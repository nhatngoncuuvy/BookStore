package com.nhom1.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhom1.bookstore.entity.Book;
import com.nhom1.bookstore.entity.OrderDetail;
import com.nhom1.bookstore.entity.OrderDetail.BookInOrder;
import com.nhom1.bookstore.services.BookService;
import com.nhom1.bookstore.services.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UpdateOrderStatusController {
    private final OrderService orderService;
    private final BookService bookService;

    public UpdateOrderStatusController(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @PostMapping("/quantri/donhang/capnhantrangthai")
    public String register(HttpSession session,
    @RequestParam("ID") String maDonHang,
    @RequestParam("orderStatus") String orderStatusRaw) {
        int orderStatus = Integer.parseInt(orderStatusRaw);
        if(orderStatus == 3) {
            OrderDetail orderDetail = orderService.getOrderDetail(maDonHang);

            for (BookInOrder bookInOrder : orderDetail.getBookList()) {
                Book book = bookService.getBook(bookInOrder.getIdSach());
                bookService.updateSoldQuantity(book.getId(), book.getDaBan() + bookInOrder.getSoLuong());
            }
        }
        orderService.editStatusOrder(maDonHang, orderStatus);
        return "redirect:/quantri/donhang";
    }
}
