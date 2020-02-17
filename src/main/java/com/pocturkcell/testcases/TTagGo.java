package com.pocturkcell.testcases;

import com.pocturkcell.model.pages.TTagGoPage;
import com.pocturkcell.model.pages.TestBase;
import org.testng.annotations.Test;

public class TTagGo extends TestBase
{
    private TTagGoPage homePage;

    public TTagGo()
    {
        this.homePage = new TTagGoPage();
    }

    @Test
    public void newOrder() throws Exception
    {
        homePage.openPage();
    }

}
