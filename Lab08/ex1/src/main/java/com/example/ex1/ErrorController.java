package com.example.ex1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {

//    @RequestMapping(value = "/error", method = RequestMethod.GET)
//    public String handleError(Model model, HttpServletRequest request) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        if (status != null) {
//            int statusCode = Integer.parseInt(status.toString());
//            HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
//
//            String errorMsg = "Http Error Code: " + statusCode + ". " + httpStatus.getReasonPhrase();
//            model.addAttribute("errorMsg", errorMsg);
//        } else {
//            model.addAttribute("errorMsg", "Error");
//        }
//
//        return "error";
//    }


    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getMessage(Model model, HttpServletRequest httpServletRequest){
        String errorMsg = "" ;
        int httpErrorCode = getErrorCode(httpServletRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
            default: {
                errorMsg = "Error";
                break;
            }
        }

        model.addAttribute("errorMsg", errorMsg);
        return "error";
    }

    private int getErrorCode(HttpServletRequest httpServletRequest){
        Object errorCode = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return errorCode == null ? 0 : (Integer) errorCode;
    }
}
