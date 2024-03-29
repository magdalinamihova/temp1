//package at.spengergasse.sj2324posproject.presentation.api.dtos;
//
//import at.spengergasse.sj2324posproject.domain.entities.Book;
//import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
//import at.spengergasse.sj2324posproject.domain.enums.Language;
//import lombok.Data;
//
//import java.util.Date;
//
//@Data
//public record BookDto(
//        Long id,
//        String bookTitle,
//        String author,
//        String bookDescription,
//        Language language,
//        String genre,
//        Object bookCover,
//        boolean hardCover,
//        Date dueDate,
//        BookStatus bookStatus,
//        Long postedById
//) {
//
//    public BookDto(Book book) {
//        this(
//                book.getId(),
//                book.getBookTitle(),
//                book.getAuthor(),
//                book.getBookDescription(),
//                book.getLanguage(),
//                book.getGenre(),
//                book.getBookCover(),
//                book.isHardCover(),
//                book.getDueDate(),
//                book.getBookStatus(),
//                book.getPostedBy() != null ? book.getPostedBy().getId() : null
//        );
//    }
//}
