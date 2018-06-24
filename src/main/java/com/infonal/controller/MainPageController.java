package com.infonal.controller;

import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.infonal.model.User;
import com.infonal.service.MainPageService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

//GUI management on the controller class.

@Controller
public class MainPageController {

	@Autowired
	MainPageService mainPageService;
	@Autowired
	ReCaptcha reCaptcha;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ModelAndView getMainPage() {
		ModelAndView myModelAndView = new ModelAndView("index");
		myModelAndView.addObject("myUserList", mainPageService.getAllUsers());
		myModelAndView.addObject("userForm", new User());

		return myModelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/submitedform")
	public ModelAndView getMainPageSubmitForm(@ModelAttribute User myUser,
			HttpServletRequest myrequest) {
		ModelAndView myModelAndView = new ModelAndView("index");

		myModelAndView.addObject("userForm", new User());
		String remoteAddr = myrequest.getRemoteAddr();

		String recaptchaResponse = myrequest.getParameter("g-recaptcha-response");


		if (isCaptchaValid("6LcPb2AUAAAAAHIuVNzu9Wkvw8teT-x76T4VXbAY", recaptchaResponse, remoteAddr)) {
			mainPageService.insertUser(myUser);
		}  else {
			myModelAndView.addObject("wrongCaptcha", "Your Capcha is WRONG!");
		}
		myModelAndView.addObject("myUserList", mainPageService.getAllUsers());
		return myModelAndView;
	}
	public static boolean isCaptchaValid(String secretKey, String response, String remoteAddr) {
		try {
			String url = "https://www.google.com/recaptcha/api/siteverify?"
					+ "secret=" + secretKey
					+ "&response=" + response
					+ "&remoteIp" + remoteAddr;
			InputStream res = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));

			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
			String jsonText = sb.toString();
			res.close();

			JSONObject json = new JSONObject(jsonText);
			return json.getBoolean("success");
		} catch (Exception e) {
			return false;
		}
	}
	@RequestMapping(method = RequestMethod.POST, value = "/deleteUser")
	public @ResponseBody
	String getMainPageDeleteForm(
			@RequestParam(value = "id", required = true) String myUser) {
		System.out.println(myUser);
		mainPageService.removeUser(myUser);
		return "true";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateUser")
	public @ResponseBody
	String getMainPageUpdateUser(
			@RequestParam(value = "id", required = true) String myUser,
			@RequestParam(value = "editUserName", required = true) String editUserName,
			@RequestParam(value = "editUserSurName", required = true) String editUserSurName,
			@RequestParam(value = "editTel", required = true) String editTel) {

		User user = new User();
		user.setUserId(myUser);
		user.setPhoneNo(editTel);
		user.setUserName(editUserName);
		user.setUserSurname(editUserSurName);
		mainPageService.updateUser(user);
		return "true";
	}

}
