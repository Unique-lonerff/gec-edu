package personal.zx.myocean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import personal.zx.myocean.req.DocQueryReq;
import personal.zx.myocean.req.DocSaveReq;
import personal.zx.myocean.resp.DocQueryResp;
import personal.zx.myocean.resp.PageResp;
import personal.zx.myocean.service.IDocService;
import personal.zx.myocean.utils.CommonResp;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private IDocService docService;

    @GetMapping("/all/{ebookId}")
    public CommonResp allByEbookId(@PathVariable Long ebookId) {
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
        List<DocQueryResp> list = docService.allbyEbookId(ebookId);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/all")
    public CommonResp all() {
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>(true,"查询成功",null);

        List<DocQueryResp> list = docService.all();
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/list")
    public CommonResp list(@Valid DocQueryReq req) {
        CommonResp<PageResp<DocQueryResp>> resp = new CommonResp<>(true,"查询成功",null);

        PageResp<DocQueryResp> pageResp = docService.listByname(req);
        resp.setContent(pageResp);

        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq req) {
        CommonResp resp = new CommonResp<>(true,"成功",null);
        docService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>(true,"删除成功",null);
        docService.delete(id);
        return resp;
    }

    @GetMapping("/remove")
    public CommonResp delete(@RequestParam("ids") List<Long> ids) {
        CommonResp resp = new CommonResp<>(true,"删除成功",null);

        docService.delete(ids);
        return resp;
    }
    @GetMapping("/vote/{id}")
    public CommonResp vote(@PathVariable Long id) {
        CommonResp commonResp = new CommonResp();
        docService.vote(id);
        return commonResp;
    }
}
