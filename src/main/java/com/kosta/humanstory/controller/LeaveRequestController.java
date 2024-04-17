package com.kosta.humanstory.controller;

import com.kosta.humanstory.domain.LeaveRequestDTO;
import com.kosta.humanstory.service.LeaveRequestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/leave/")
@AllArgsConstructor
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService service;

    @GetMapping("/emp_delivery")
    public void logoutGET() {

    }

    /*@Secured("_USER")*/
    @GetMapping("/calendar")
    public String list(Model model,@RequestParam("empNum") String empNum) {
        System.out.println("===========" + model + "===========" );
        System.out.println("컨트롤러 /조회/달력/ 작동");
        model.addAttribute("list", service.getEmpLeaveCharts(empNum)); // 이벤트 데이터를 모델에 추가

        return "/leave/calendar"; // 해당 JSP 페이지 이름 반환
    }


    /*@Secured("_MANAGER")*/
    /*@GetMapping("/calendar")
    public String managerList(Model model) {
        System.out.println("===========" + model + "===========" );
        System.out.println("컨트롤러 /조회/달력/ 작동");
        model.addAttribute("list", service.getLeaveCharts()); // 이벤트 데이터를 모델에 추가
        return "/leave/calendar"; // 해당 JSP 페이지 이름 반환
    }*/

    @PostMapping("/register")
    public String register(LeaveRequestDTO leave, RedirectAttributes rttr)throws Exception {
        System.out.println("===========" +leave + "===========" );
        System.out.println("컨트롤러 /등록/ 작동");
        try {
            service.registerLeave(leave);
            rttr.addFlashAttribute("result", leave.getEmpNum()); //값을 담아갈때
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/leave/calendar";
    }

    @GetMapping({"/getLeaveOne","/modify"})
    public void get(@RequestParam("leaveNum") Long leaveNum, Model model) {
        System.out.println("===========" +leaveNum + "===========" );
        System.out.println("컨트롤러 /수정/지정조회/ 작동");
        model.addAttribute("leave", service.getLeaveOne(leaveNum));
    }

    @PostMapping("/modify")
    public String modify(LeaveRequestDTO leave, RedirectAttributes rttr) {
        System.out.println("===========" +leave + "===========" );
        System.out.println("컨트롤러 /수정/ 작동");

        if(service.modifyLeave(leave)) {
            rttr.addFlashAttribute("result", "success");
        }

        return "redirect:/leave/calendar";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("leaveNum") Long leaveNum, RedirectAttributes rttr) {
        System.out.println("===========" +leaveNum + "===========" );
        System.out.println("컨트롤러 /등록/ 작동");

        if(service.removeLeave(leaveNum)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/leave/calendar";
    }
}
