package com.raf.restdemo.helper;

import com.raf.restdemo.domain.Notification;
import com.raf.restdemo.domain.NotificationType;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MailTextFormater {
    private List<String> getPlaceHoldersInOrder(String embededMsg)
    {
        List<String> paramsPlaceholdersList = new ArrayList<>();

        int nth = 1;
        while(true)
        {
            int startIdx = 1 + StringUtils.ordinalIndexOf(embededMsg,"%",nth);
            int endIdx = StringUtils.ordinalIndexOf(embededMsg,"%",nth+1);

            if(startIdx == - 1 || endIdx == -1) break;

            String placeHolder = embededMsg.substring(startIdx,endIdx);
            paramsPlaceholdersList.add(placeHolder);
            nth += 2; //move to next placeholder
        }

        return paramsPlaceholdersList;
    }

    private List<String> getParametersInOrder(String embededMsg, Object data) throws IllegalAccessException {
        List<String> placeholderList = getPlaceHoldersInOrder(embededMsg);
        System.out.println(placeholderList);
        List<String> parametersList = new ArrayList<>();

        for(String placeholder : placeholderList)
        {
            String parameter = "-1";
            for(Field classField : data.getClass().getDeclaredFields())
            {
                if(classField.getName().equals(placeholder))
                {
                    classField.setAccessible(true);
                    parameter = (String) classField.get(data);
                    break;
                }
            }
            parametersList.add(parameter);
        }

        return parametersList;
    }

    //placeholder template:  %placeholder%
    private String swapPlaceholderToParameter(String embededMsg, String parameter)
    {
        int startIdx = StringUtils.ordinalIndexOf(embededMsg,"%",1);
        int endIdx = 1 + StringUtils.ordinalIndexOf(embededMsg,"%",2);

        return embededMsg.substring(0,startIdx) + parameter + embededMsg.substring(endIdx);
    }

    public String formatText(String embededMsg, Object data) throws IllegalAccessException {
        List<String> parametersList = getParametersInOrder(embededMsg, data);
        for(String param : parametersList)
        {
            embededMsg = swapPlaceholderToParameter(embededMsg, param);
        }

        return embededMsg;
    }
}
