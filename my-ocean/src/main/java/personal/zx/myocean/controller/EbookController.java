package personal.zx.myocean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import personal.zx.myocean.req.EbookQueryReq;
import personal.zx.myocean.resp.EbookQueryResp;
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

        CommonResp resp = new CommonResp<>(true,"查询成功",null);
        List<EbookQueryResp> list = ebookService.listByname(req);
        resp.setContent(list);

        return resp;
    }
}
