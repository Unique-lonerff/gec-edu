package personal.zx.myocean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import personal.zx.myocean.req.EbookQueryReq;
import personal.zx.myocean.req.EbookSaveReq;
import personal.zx.myocean.resp.EbookQueryResp;
import personal.zx.myocean.resp.PageResp;
import personal.zx.myocean.service.IEbookService;
import personal.zx.myocean.utils.CommonResp;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 电子书 前端控制器
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Autowired
    private IEbookService ebookService;


    @GetMapping("/list")
    public CommonResp list(@Valid EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>(true,"查询成功",null);

        PageResp<EbookQueryResp> pageResp = ebookService.listByname(req);
        resp.setContent(pageResp);

        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>(true,"成功",null);
        ebookService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }

}
