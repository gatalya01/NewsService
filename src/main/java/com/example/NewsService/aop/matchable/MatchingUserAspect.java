package com.example.NewsService.aop.matchable;

import com.example.NewsService.ex.UserUnmatchedException;
import com.example.NewsService.service.CommentService;
import com.example.NewsService.service.NewsService;
import com.example.NewsService.util.ErrorMsg;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class MatchingUserAspect {
    public static final String HTTP_HEADER_USER_ID = "X-User-Id";
    private final NewsService newsService;
    private final CommentService commentService;

    @Before("@annotation(MatchableNewsUser)")
    public void matchingNewsUser() throws UserUnmatchedException {
        HttpServletRequest request = this.getRequest();
        int headerUserId = this.getHeaderId(request);
        int newsId = this.getPathId(request);
        int newsUserId = this.newsService.findById(newsId).getUser().getId();
        if (headerUserId != newsUserId) {
            throw new UserUnmatchedException(ErrorMsg.NEWS_USER_ILLEGAL);
        }
    }

    @Before("@annotation(MatchableCommentUser)")
    public void matchingCommentUser() throws UserUnmatchedException {
        HttpServletRequest request = this.getRequest();
        int headerUserId = this.getHeaderId(request);
        int commentId = this.getPathId(request);
        int commentUserId = this.commentService.findById(commentId).getUser().getId();
        if (headerUserId != commentUserId) {
            throw new UserUnmatchedException(ErrorMsg.COMMENT_USER_ILLEGAL);
        }
    }

    private HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        return  ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    private int getPathId(HttpServletRequest request) {
        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return Integer.valueOf(pathVariables.get("id"));
    }

    /**
     * Получить id пользователя, переданный через заголовок запроса
     * @param request - объект запроса
     * @return - id пользователя из заголовка запроса
     * @throws UserUnmatchedException - при отсутствии заголовка с id или при отрицательном id в заголовке
     */
    private int getHeaderId(HttpServletRequest request) throws UserUnmatchedException {
        String headerValue = request.getHeader(MatchingUserAspect.HTTP_HEADER_USER_ID);
        try {
            int id = Integer.valueOf(headerValue);
            if (id <= 0) {
                throw new UserUnmatchedException(ErrorMsg.HEADER_USER_ID_NOT_POSITIVE);
            }
            return id;
        } catch (NumberFormatException e) {
            throw new UserUnmatchedException(ErrorMsg.HEADER_USER_ILLEGAL);
        }
    }
}