package com.itdan.springdemo03;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class Springdemo03ApplicationTests {

    @Autowired
    private JavaMailSender javaMailSender;

  //  @Autowired
  //  private TemplateEngine templateEngine;
 // TemplateEngine templateEngine = new TemplateEngine();
  // 是新生成一个 TemplateEngine 对象，这个对象没有任何配置，
  // 应当使用 @Autowired 注入对象
  //@Autowired
  //TemplateEngine templateEngine;

    /**
     * 简单发送邮件测试
     * @throws Exception
     */
    @Test
    public void testDemo01() throws Exception{
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setSubject("这是邮件主题");
        simpleMailMessage.setText("这是邮件内容");
        simpleMailMessage.setFrom("2207161187@qq.com");
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setTo("2207161187@qq.com");
        javaMailSender.send(simpleMailMessage);
     }

    /**
     * 发送邮件附件测试
     * @throws Exception
     */
    @Test
     public void testDemo02() throws Exception{
      MimeMessage message= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message,true);
        mimeMessageHelper.setSubject("这是邮件主题");
        mimeMessageHelper.setText("这是邮件内容");
        mimeMessageHelper.setFrom("2207161187@qq.com");
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setTo("2207161187@qq.com");
        mimeMessageHelper.addAttachment("测试图片",
                new File("C:\\Users\\Administrator\\Desktop\\images\\bg.png"));
        javaMailSender.send(message);
    }

    /**
     * 发送图片
     * @throws Exception
     */
    @Test
    public void testDemo03() throws Exception{
        MimeMessage message= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message,true);
        mimeMessageHelper.setSubject("这是邮件主题");
        mimeMessageHelper.setText("这是邮件内容:<img src='pic01'>",true);
        mimeMessageHelper.setFrom("2207161187@qq.com");
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setTo("2207161187@qq.com");
        mimeMessageHelper.addInline("pic01",new File("C:\\Users\\Administrator\\Desktop\\images\\bg.png"));
        javaMailSender.send(message);
     }

    /**
     * thymeleaf测试
     * @throws Exception
     */
    @Test
     public void testDemo04() throws Exception{
        TemplateEngine templateEngine = new TemplateEngine();
        MimeMessage message= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message,true);
        mimeMessageHelper.setSubject("这是邮件主题（thymeleaf测试）");
        mimeMessageHelper.setFrom("2207161187@qq.com");
        Context context=new Context();
        context.setVariable("username","dan");
        context.setVariable("position","码农");
        context.setVariable("salary",1000);
        context.setVariable("dept","研发");
         String proess=templateEngine.process("mail.html",context);
         mimeMessageHelper.setText(proess,true);
         mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setTo("2207161187@qq.com");
        javaMailSender.send(message);
      }

    /**
     * freemaker测试
     * @throws Exception
     */
    @Test
      public void testDemo05() throws Exception{

        MimeMessage message= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message,true);
        mimeMessageHelper.setSubject("这是邮件主题（freemaker测试）");
        mimeMessageHelper.setFrom("2207161187@qq.com");
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(),"templates");
        Template template= configuration.getTemplate("mail01.ftl");

        Map<String,Object> context=new HashMap<>();

        context.put("username","dan");
        context.put("position","码农");
        context.put("salary",1000);
        context.put("dept","研发");

        StringWriter out=new StringWriter();
        template.process(context,out);

        mimeMessageHelper.setText(out.toString(),true);
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setTo("2207161187@qq.com");
        javaMailSender.send(message);
       }


}
