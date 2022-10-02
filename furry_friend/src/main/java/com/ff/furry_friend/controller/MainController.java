package com.ff.furry_friend.controller;

import com.ff.furry_friend.entity.comment;
import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.oauth2.SessionUser;
import com.ff.furry_friend.service.BasketService;
import com.ff.furry_friend.service.CommentService;
import com.ff.furry_friend.service.ProductService;
import com.ff.furry_friend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class MainController {
    @Autowired
    private final ProductService productService;

    @Autowired
    private final CommentService commentService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final HttpSession httpSession;

    @GetMapping("")  //메인페이지로 만들어야함!!!
    public String category(Model model){
        List<product> li = productService.findAll();
        model.addAttribute("li", li);

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            System.out.println(user.getEmail());
            model.addAttribute("id", user.getEmail());
        }

        return "category";
    }

    @PostMapping("search")
    public String Search(@RequestParam(value = "search", required = false) String search, Model model){
        List<product> li = productService.findPartName(search);
        model.addAttribute("li", li);
        System.out.println(search);
        return "category";
    }

    @GetMapping("category")
    public String category(@RequestParam(value = "category", required = false) int category, Model model){
        if(category < 10){//카테고리 값이 없다면 실행 x 수정 필요
            List<product> li = productService.findAll();
            model.addAttribute("li", li);
        }else{
            List<product> li = productService.findCategory(category);
            model.addAttribute("li", li);
        }

        return "category";
    }

    @GetMapping("category/detail")
    public String detail(@RequestParam(value = "id", required=false) Long id, Model model, HttpSession session){
        List<product> li = productService.findId(id);
        model.addAttribute("li", li);

        List<comment> comment = commentService.findComment(id);
        model.addAttribute("comment", comment);

        model.addAttribute("userid", (String)session.getAttribute("id"));

        return "detail";
    }

    @PostMapping("/category/detail/save")
    public String save(comment comment, @RequestParam(value = "pro_name", required=false) String pro_name, HttpSession session){
        List<product> li = productService.findName(pro_name);
        Optional<user> result = userService.findUsers((String) session.getAttribute("id"));
        comment.setProduct(li.get(0));
        comment.setUser(result.get());
        commentService.save(comment);

        return "redirect:/category/detail?id=" + li.get(0).getPro_id();
    }

    @GetMapping("purchase")
    public String Purchase(@RequestParam(value = "name") String name){  //구매
        System.out.println(name);
        return null;
    }

    @PostMapping("/category/detail/delete")
    public String comment_delete(@RequestParam(value = "commentid", required=false) int commentid) {
        System.out.println(commentid);
        commentService.delete(commentid);
        List<comment> li = commentService.findCommentId(commentid);

        return "redirect:/category/detail?id=" + li.get(0).getProduct().getPro_id();  //삭제시 원래 페이지 redirect필요
    }

    @PostMapping("/category/detail/update")
    public String comment_update(){
        return "";
    }
}
