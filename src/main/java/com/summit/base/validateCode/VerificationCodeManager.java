package com.summit.base.validateCode;

import com.summit.base.Constants;
import com.summit.base.validateCode.validateCodeUtils.Captcha;
import com.summit.base.validateCode.validateCodeUtils.GifCaptcha;
import com.summit.base.validateCode.validateCodeUtils.SpecCaptcha;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;

@Controller
@RequestMapping("vcode")
public class VerificationCodeManager {

    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * 获取验证码（Gif版本）
     *
     * @param response
     */
    @RequestMapping(value = "getGifCode", method = RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Font font = new Font("Verdana", Font.ITALIC | Font.BOLD, 20); // 字体
            Captcha captcha = new GifCaptcha(80, 25, 4, font);
            //输出
            captcha.out(response.getOutputStream());
            HttpSession session = request.getSession(true);
            //存入Session
            session.setAttribute(Constants.VALIDATECODE_SEEESION_ATTRIBUTE, captcha.text().toLowerCase());
        } catch (Exception e) {
            logger.error("验证码生成失败!");
        }
    }

    /**
     * 获取验证码（jpg版本）
     *
     * @param response
     */
    @RequestMapping(value = "getJPGCode", method = RequestMethod.GET)
    public void getJPGCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpg");

            /**
             * jgp格式验证码
             * 宽，高，位数。
             */

            Font font = new Font("Verdana", Font.ITALIC | Font.BOLD, 20); // 字体
            Captcha captcha = new SpecCaptcha(80, 25, 4, font);
            //输出
            captcha.out(response.getOutputStream());
            HttpSession session = request.getSession(true);
            //存入Session
            session.setAttribute(Constants.VALIDATECODE_SEEESION_ATTRIBUTE, captcha.text().toLowerCase());
        } catch (Exception e) {
            logger.error("验证码生成失败!");
        }
    }
}
