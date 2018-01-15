package zoo.plus.task.currencyconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import zoo.plus.task.currencyconverter.model.CurrencyRequest;
import zoo.plus.task.currencyconverter.model.CurrencyResponse;
import zoo.plus.task.currencyconverter.model.security.User;
import zoo.plus.task.currencyconverter.service.CurrencyService;
import zoo.plus.task.currencyconverter.service.CurrencyServiceImpl;
import zoo.plus.task.currencyconverter.service.HistoryService;
import zoo.plus.task.currencyconverter.service.UserService;
import zoo.plus.task.currencyconverter.util.DateUtil;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;

@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "/main/home", method = RequestMethod.GET)
    public ModelAndView mainHome() {
        ModelAndView modelAndView = new ModelAndView();
        prepareHome(modelAndView);
        modelAndView.addObject("currencyRequest", new CurrencyRequest());
        return modelAndView;
    }

    @RequestMapping(value = "/main/load-rates", method = RequestMethod.POST)
    public ModelAndView loadRates(@Valid CurrencyRequest currencyRequest, BindingResult bindingResult) throws ParseException, IOException {

        ModelAndView modelAndView = new ModelAndView();

        if (!bindingResult.hasErrors() && !StringUtils.isEmpty(currencyRequest.getDate())) {
            if (DateUtil.isAfterTheCurrentDate(DateUtil.toDate(currencyRequest.getDate(), CurrencyServiceImpl.API_DATE_FORMAT))) {
                bindingResult
                        .rejectValue("date", "error.currencyRequest",
                                "You should provide a date in the past");
            }
        }

        if (bindingResult.hasErrors()) {
            prepareHome(modelAndView);
            return modelAndView;
        }

        final CurrencyResponse ratesResponse;
        if (StringUtils.isEmpty(currencyRequest.getDate())) {
            ratesResponse = currencyService.getExchangeRates();
        } else {
            ratesResponse = currencyService.getExchangeRates(currencyRequest.getDate());
        }
        ratesResponse.setUserId(retrieveCurrentUser().getId());
        historyService.saveHistoryRecord(ratesResponse);

        modelAndView.setViewName("redirect:/main/home");

        return modelAndView;
    }

    private void prepareHome(ModelAndView modelAndView) {
        User user = retrieveCurrentUser();
        modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for registered Users with USER Role");
        modelAndView.addObject("history", historyService.getLastTenRecords(user.getId()));
        modelAndView.setViewName("main/home");
    }

    private User retrieveCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByEmail(auth.getName());
    }
}
