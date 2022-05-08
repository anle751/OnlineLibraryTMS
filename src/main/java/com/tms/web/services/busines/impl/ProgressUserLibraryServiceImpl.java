package com.tms.web.services.busines.impl;

import com.tms.web.entities.library.Book;
import com.tms.web.entities.library.Chapter;
import com.tms.web.entities.library.UserLibrary;
import com.tms.web.entities.repositories.UserLibraryRepository;
import com.tms.web.entities.security.User.User;
import com.tms.web.services.busines.ProgressUserLibraryService;
import com.tms.web.services.entities.BookService;
import com.tms.web.services.entities.ChapterService;
import com.tms.web.services.entities.UserLibraryService;
import com.tms.web.services.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProgressUserLibraryServiceImpl implements ProgressUserLibraryService {

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    UserLibraryService userLibraryService;
    @Autowired
    UserLibraryRepository userLibraryRepository;

    @Transactional
    @Override
    public Chapter checkProgress(Long bookId) {
        Chapter chapter = null;
        User user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            user = userService.get(username);
        }
        if (Objects.nonNull(user)) {
            List<UserLibrary> userLibraryList = user.getUserLibrary();
            UserLibrary userLibrary = containsBook(userLibraryList, bookId);
            Book book = bookService.get(bookId);
            if (Objects.isNull(userLibrary)) {
                userLibrary = UserLibrary.builder()
                        .book(book)
                        .user(user)
                        .chapterId(book.getChapterList().get(0).getId())
                        /*.chapterId(1L)*/
                        .build();
                userLibraryRepository.save(userLibrary);
                userLibraryList.add(userLibrary);
                chapter= book.getChapterList().get(0);
            } else {
                Long chapterId = userLibrary.getChapterId();
                chapter = chapterService.get(chapterId);
            }
        }
        return chapter;
    }

    @Transactional
    @Override
    public Chapter updateProgress(Long bookId, Long chapterId, Boolean nextPrev) {
        User user = null;
        Chapter chapter = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            user = userService.get(username);
        }
        if (Objects.nonNull(user)) {
            List<UserLibrary> userLibraryList = user.getUserLibrary();
            UserLibrary userLibrary = containsBook(userLibraryList, bookId);
            Book book = null;
            if (Objects.nonNull(userLibrary)) {
                book = userLibrary.getBook();
                List<Chapter> chapterList = book.getChapterList();

                Integer chapterListIndex = null;
                for (Chapter chapterItem : chapterList) {
                    if (chapterItem.getId().equals(chapterId)) {
                        chapterListIndex = chapterList.indexOf(chapterItem);
                        break;
                    }
                }
                if (Objects.nonNull(chapterListIndex)) {
                    int returnedChapterIndex;
                    if (nextPrev) {
                        if (chapterListIndex < chapterList.size() - 1) {
                            returnedChapterIndex = chapterListIndex + 1;
                            userLibraryService.updateChapter(userLibrary.getId(), chapterList.get(returnedChapterIndex).getId());
                            chapter = chapterList.get(returnedChapterIndex);
                        }
                    } else {
                        if (chapterListIndex > 0) {
                            returnedChapterIndex = chapterListIndex - 1;
                            userLibraryService.updateChapter(userLibrary.getId(), chapterList.get(returnedChapterIndex).getId());
                            chapter = chapterList.get(returnedChapterIndex);
                        }
                    }
                }
            }
        }
        return chapter;
    }

    private UserLibrary containsBook(List<UserLibrary> userLibraryList, Long bookId) {
        UserLibrary userLibrary = null;
        for (UserLibrary userLibraryItem : userLibraryList) {
            if (Objects.equals(userLibraryItem.getBook().getId(), bookId)) {
                userLibrary = userLibraryItem;
            }
        }
        return userLibrary;
    }
}
