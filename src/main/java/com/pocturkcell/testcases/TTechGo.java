package com.pocturkcell.testcases;

import com.pocturkcell.model.pages.TTechGoPage;
import com.pocturkcell.model.pages.TestBase;
import org.testng.annotations.Test;

public class TTechGo extends TestBase
{
    private TTechGoPage homePage;

    public TTechGo()
    {
        this.homePage = new TTechGoPage();
    }

    @Test
    public void newOrder() throws Exception
    {
        homePage.openPage();
    }

}
