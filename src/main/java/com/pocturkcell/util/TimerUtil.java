package com.pocturkcell.util;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class TimerUtil
{
    private static final Logger LOGGER = Logger.getLogger(TimerUtil.class);

    public static void waitUntilMilliseconds(long milliseconds)
    {
        try
        {
            LOGGER.debug("Sleep " + milliseconds + " milliseconds");
            TimeUnit.MILLISECONDS.sleep(milliseconds);
            LOGGER.debug("Timer completed");
        }
        catch (Exception e)
        {
            LOGGER.error("Error on timer", e);
        }
    }

    public static void waitUntilSeconds(long seconds)
    {
        try
        {
            LOGGER.debug("Sleep " + seconds + " seconds");
            TimeUnit.SECONDS.sleep(seconds);
            LOGGER.debug("Timer completed");
        }
        catch (Exception e)
        {
            LOGGER.error("Error on timer", e);
        }
    }

    public static void waitUntilNextHours()
    {
        try
        {
            LOGGER.debug("Sleeping till next hour");
            Calendar cal = Calendar.getInstance();
            int currentMinute = cal.get(Calendar.MINUTE);
            LOGGER.debug("Current minute : " + currentMinute);
            int remainingMinuteTillNextHour = 60 - currentMinute;
            LOGGER.debug("remainingMinuteTillNextHour : " + remainingMinuteTillNextHour);
            LOGGER.debug("Sleep " + remainingMinuteTillNextHour + " Minutes");
            TimeUnit.MINUTES.sleep(remainingMinuteTillNextHour);
            LOGGER.debug("Timer completed");
        }
        catch (Exception e)
        {
            LOGGER.error("Error on timer", e);
        }
    }

}
