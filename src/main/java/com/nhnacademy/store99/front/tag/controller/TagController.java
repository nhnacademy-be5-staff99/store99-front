package com.nhnacademy.store99.front.tag.controller;

import com.nhnacademy.store99.front.tag.dto.request.CreateTagRequest;
import com.nhnacademy.store99.front.tag.dto.request.ModifyTagRequest;
import com.nhnacademy.store99.front.tag.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 태그 컨트롤러, Model 사용
 *
 * @author rosin23
 */

@Controller
@AllArgsConstructor
@RequestMapping("/admin/tags")
public class TagController {

    private TagService tagService;

    @GetMapping
    public String showTaglist(Model model) {
        model.addAttribute("tags", tagService.findAllTags());
        return "admin/tag/tag_admin";
    }

    @PostMapping
    public String createTag(@ModelAttribute CreateTagRequest request,
                            RedirectAttributes redirectAttributes) {
        tagService.createTag(request);
        redirectAttributes.addFlashAttribute("message", "태그 생성 완료");
        return "redirect:/admin/tags";
    }

    @PutMapping("/{id}")
    public String updateTag(@PathVariable Long id, @ModelAttribute ModifyTagRequest request,
                            RedirectAttributes redirectAttributes) {
        tagService.updateTag(id, request);
        redirectAttributes.addFlashAttribute("message", "태그 변경 완료");
        return "redirect:/admin/tags";
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message", "태그 삭제 완료");
        return "redirect:/admin/tags";
    }

}
