package com.fable.kscc.bussiness.controller.loginController;

import com.fable.kscc.api.model.page.ResultKit;
import com.fable.kscc.api.model.page.ServiceResponse;
import com.fable.kscc.api.model.user.FbsUser;
import com.fable.kscc.api.utils.Constants;
import com.fable.kscc.api.utils.FileUtil;
import com.fable.kscc.api.utils.HttpUtils;
import com.fable.kscc.bussiness.service.loginService.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Wanghairui on 2017/8/1.
 */
@Controller
@RequestMapping("/loginController")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    private ExecutorService service = Executors.newSingleThreadExecutor();

    private Map<HttpSession, Long> sessionLongMap = new HashMap<>();

    private Map<HttpSession, Long> sessionLongMapTemp=new HashMap<>();

    @RequestMapping("/login")
    @ResponseBody
    public FbsUser toLogin(HttpServletRequest request, @RequestBody FbsUser user) {
        FbsUser ksUser=loginService.toLogin(user);
        if(ksUser!=null){
            request.getSession().setAttribute("ksUser",ksUser);
        }
        return ksUser;
    }

    @RequestMapping("/logout")
    public String toLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/loginController/toLogin";
    }
    @RequestMapping("/toLogin")
    public String toLoginJsp() {
        return "login";
    }
    
    @RequestMapping("/toHome")
    public String toIndexJsp() {
        return "index";
    }
    
    @RequestMapping("/toHomePage")
    public String toHomePageJsp() {
        return "homePage";
    }
    
    @RequestMapping("/toCreateLiveBroadcast")
    public String toCreateLiveBroadcastJsp() {
        return "createLiveBroadcast/createLiveBroadcast";
    }
    
    @RequestMapping("/toPartModal")
    public String toPartModalJsp() {
        return "createLiveBroadcast/partModal";
    }
    
    @RequestMapping("/toEditLiveBroadcast")
    public String toEditLiveBroadcastJsp() {
        return "editLiveBroadcast/editLiveBroadcast";
    }
    
    @RequestMapping("/toLiveApproval")
    public String toLiveApprovalJsp() {
        return "liveApproval/liveApproval";
    }
    
    @RequestMapping("/toLiveConferenceKSCC")
    public String toLiveConferenceKSCCJsp() {
        return "liveConference/liveConferenceKSCC";
    }
    
    @RequestMapping("/toLiveConferenceHosUser")
    public String toLiveConferenceJsp() {
        return "liveConference/liveConferenceHosUser";
    }
   
    @RequestMapping("/toLiveControlList")
    public String toLiveControlListJsp() {
        return "liveControl/liveControlList";
    }
    
    @RequestMapping("/toLiveControlDetail")
    public String toLiveControlListDetailJsp() {
        return "liveControl/liveControlDetail";
    }

    @RequestMapping("/toMessageCenter")
    public String toMessageCenterJsp() {
        return "messageCenter/messageList";
    }
    
    @RequestMapping("/toAccountManage")
    public String toAccountManageJsp() {
        return "accountManage/accountManageList";
    }
    
    @RequestMapping("/toCodec")
    public ModelAndView toCodecJsp(HttpServletRequest request,String id) {
            ModelAndView mv = new ModelAndView("/accountManage/codec");
        request.setAttribute("id",id);
        return mv;
    }
    
    @RequestMapping("/toDoctorUser")
    public ModelAndView toDoctorUserJsp(HttpServletRequest request,String id) {
        ModelAndView mv = new ModelAndView("/accountManage/doctorUser");
        request.setAttribute("id",id);
        return mv;
    }
    
    @RequestMapping("/toHospitalAdmin")
    public ModelAndView toHospitalAdminJsp(HttpServletRequest request,String id) {
        ModelAndView mv = new ModelAndView("/accountManage/hospitalAdminList");
        request.setAttribute("id",id);
        return mv;
    }
    @RequestMapping("/toHospitalInfo")
    public ModelAndView toHospitalInfo(HttpServletRequest request,String id) {
        ModelAndView mv = new ModelAndView("/accountManage/hospitalInfo");
        request.setAttribute("id",id);
        return mv;
    }
    //医院列表跳转页面
    @RequestMapping("/toHospitalList")
    public String toHospitalListJsp() {
        return "accountManage/hospitalList";
    }

    @RequestMapping("/toHospitalDetail")
    public String toHospitalDetailJsp() {
        return "accountManage/hospitalDetail";
    }
    
    @RequestMapping("/toHomeConfig")
    public String toHomeConfigJsp() {
        return "homeConfig/homeConfig";
    }
    
    @RequestMapping("/toResourceCalendar")
    public String toResourceCalendarJsp() {
        return "resourceCalendar/resourceCalendar";
    }
    
    @RequestMapping("/toResourceModal")
    public String toResourceModalJsp() {
        return "resourceCalendar/calenModal";
    }
    
    @RequestMapping("/toResourceCataline")
    public  String toResourceCataline()
    {
        return "resourceCataline/resCatalineDetail";
    }

    @RequestMapping("/toRecordManager")
    public String toRecordManager() {
        return "videoManage/videoTable";
    }

    @RequestMapping("/getImage")
    public void getImage(HttpServletResponse response, HttpSession session) throws IOException {

        Random random=new Random();
        BufferedImage image=new BufferedImage(75, 35, BufferedImage.TYPE_INT_RGB);
        Graphics graphics=image.getGraphics();
        graphics.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
        graphics.fillRect(0, 0, 100, 43);
        graphics.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
        graphics.setFont(new Font("Courier New",Font.BOLD+ Font.ITALIC,23));
        String s="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer code=new StringBuffer();
        for (int i = 0; i < 5; i++) {
            int index=random.nextInt(s.length());
            code.append(s.charAt(index));
        }
        session.setAttribute("code", code.toString());
        graphics.drawString(code.toString(), 3, 25);
        ImageIO.write(image , "JPEG", response.getOutputStream());

    }

    @RequestMapping("/getBmpImage")
    @ResponseBody
    public ServiceResponse getBmpImage(@RequestBody Map<String,String> param) throws IOException {
        try{
         /*word为发送的字幕*/
            String word = param.get("word");
            System.out.println(word.length());
            BufferedImage image=new BufferedImage(55*word.length(), 70, BufferedImage.TYPE_BYTE_BINARY);
            Graphics graphics=image.getGraphics();
            graphics.setColor(new Color(0,0,0));
            graphics.fillRect(0, 0, 600, 400);
            graphics.setColor(new Color(255,255,255));
            graphics.setFont(new Font("楷体",Font.BOLD+ Font.ITALIC,50));
            graphics.drawString(word, 10, 52);

            int[] cmap = new int[]{0, 8388608, '耀', 8421376, 128, 8388736, '肀', 8421504, 12632256, 16711680, '\uff00', 16776960, 255, 16711935, '\uffff', 16777215};
            IndexColorModel icm = new IndexColorModel(4, cmap.length, cmap, 0, false, 1, 0);
            BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(), 12, icm);
            ColorConvertOp cco = new ColorConvertOp(image.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(),null);
            cco.filter(image, dest);
            ImageIO.write(dest, "BMP", new File(System.getProperty("user.home")+File.separator+"word.bmp"));
        /*编解码ip和端口号port*/
            HttpUtils.upload("http://"+param.get("ip")+":"+param.get("port")+ Constants.MedT100.uploadWord, System.getProperty("user.home")+File.separator+"word.bmp");
            return ResultKit.success();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResultKit.fail("发送字幕失败");
        }
    }

    @RequestMapping(value="/validateCode",produces="text/plain;charset=utf-8")
    public @ResponseBody String validateCode(String code,HttpSession session){
        String sessionCode=(String) session.getAttribute("code");
        if(sessionCode!=null&&sessionCode.equalsIgnoreCase(code)){
            return "success";
        }else{
            return "fail";
        }
    }

    @RequestMapping("/showPic")
    public void showPic(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String url = System.getProperty("user.dir");
        String path = url.substring(0, url.lastIndexOf(File.separator)) + File.separator + "user" + File.separator + "uploadFile";
        FileInputStream inputStream = new FileInputStream(path+File.separator+name);
        int i = inputStream.available();
        byte[] buff = new byte[i];
        inputStream.read(buff);
        inputStream.close();
        response.setContentType("image/*");
        OutputStream out = response.getOutputStream();
        out.write(buff);
        out.close();
    }
    @RequestMapping("/upload")
    @ResponseBody
    public ServiceResponse uploadImg(@RequestParam("file") CommonsMultipartFile file) throws IllegalStateException, IOException {
        Map<String,Object> response=FileUtil.upload(file);
        return ResultKit.serviceResponse(response);
    }
    @RequestMapping("/longLink/{num}")
    @ResponseBody
    public ServiceResponse longLink(HttpSession session,@PathVariable  String num){
        if(sessionLongMap.size()==0){
            sessionLongMap.put(session, Long.valueOf(num));
            checkSession();
        }
        else{
            sessionLongMap.put(session, Long.valueOf(num));
        }
        return ResultKit.serviceResponse("ndd");
    }
    private void checkSession(){
        service.execute(new Runnable() {
            @Override
            public void run() {
                while (sessionLongMap.size()>0){
                        try {
                            //此处不能用sessionMap=sessionLongMapTemp，引用会跟着sessionLongMapTemp的变动而变动
                            cloneMap(sessionLongMap,sessionLongMapTemp);
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Iterator<HttpSession> it=sessionLongMap.keySet().iterator();
                        while (it.hasNext()){
                            HttpSession session = it.next();
                            long before = 1L;
                            if(sessionLongMapTemp.get(session)!=null){
                                before=sessionLongMapTemp.get(session);
                            }
                            long now = sessionLongMap.get(session);
                            if(before==now){
                                it.remove();
                                session.invalidate();
                            }
                        }
                }
            }
        });
    }

    private void cloneMap(Map<HttpSession, Long> sessionLongMap,Map<HttpSession, Long> sessionLongMapTemp){
        Iterator<HttpSession> it = sessionLongMap.keySet().iterator();
        while (it.hasNext()){
            HttpSession session = it.next();
            sessionLongMapTemp.put(session, sessionLongMap.get(session));
        }
    }

    public static void main(String[] args) throws IOException {
        String word = "天气变凉开会记得添衣服";
        BufferedImage image=new BufferedImage(672, 64, BufferedImage.TYPE_INT_RGB);
        Graphics graphics=image.getGraphics();
        graphics.setColor(new Color(0,0,0));
        graphics.fillRect(0, 0, 672, 64);
        graphics.setColor(new Color(255,255,255));
        graphics.setFont(new Font("楷体",Font.BOLD+ Font.ITALIC,50));
        graphics.drawString(word, 10, 52);
        int[] cmap = new int[]{0, 8388608, '耀', 8421376, 128, 8388736, '肀', 8421504, 12632256, 16711680, '\uff00', 16776960, 255, 16711935, '\uffff', 16777215};
        IndexColorModel icm = new IndexColorModel(4, cmap.length, cmap, 0, false, 1, 0);
        BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(), 12, icm);
        ColorConvertOp cco = new ColorConvertOp(image.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(),null);
        cco.filter(image, dest);
        ImageIO.write(dest, "BMP", new File(System.getProperty("user.home")+File.separator+"word.bmp"));
        HttpUtils.upload("http://192.168.40.157:80/"+ Constants.MedT100.uploadWord, System.getProperty("user.home")+File.separator+"word.bmp");
    }
}
