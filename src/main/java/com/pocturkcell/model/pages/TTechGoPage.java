package com.pocturkcell.model.pages;

import com.pocturkcell.util.TimerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TTechGoPage extends CustomBasePageMethods
{

    By msisdnInput = By.id("inlineFormInputGroup");
    By paketleriGoruntuleBtn = By.xpath(".//a[text()='Paketleri Görüntüle']");
    By carouselItem = By.xpath("(.//div[@class='carousel-inner']//mdb-carousel-item)[1]");
    By firstCarouselCard = By.xpath("((.//div[@class='carousel-inner']//mdb-carousel-item[contains(@class,'active')])[1]/div)[1]");
    String firstCarouselBtn = ".//button";
    By sepetCount = By.xpath(".//div/a[text()='SEPETİM']/following-sibling::button//span");
    By sepetim = By.xpath(".//div/a[text()='SEPETİM']");
    By toplamTutarText = By.xpath(".//label[text()='TOPLAM TUTAR']");
    By odenecekTutarTextCardTop = By.xpath(".//li[text()='Aylık Ödenecek Tutar']/following-sibling::li/span");
    By odenecekTutarTextCardToplam = By.xpath(".//label[text()='TOPLAM TUTAR']/../following-sibling::div//label[contains(text(),'TL')]");
    By islemiYapBtn = By.xpath(".//button[text()='İşlemi Yap']");
    By musteriTeyidiModel = By.xpath(".//h4[text()='Müşteri Teyidi']");
    By musteriTeyidiModelButton = By.xpath(".//button[text()='Tamam']");

    public void openPage() throws Exception
    {
        System.out.println("Test started");
        super.clickWebElement(msisdnInput);
        super.sendKeyToElement(msisdnInput, "5325320000");
        super.clickWebElement(paketleriGoruntuleBtn);
        super.waitUntilVisibleByLocator(carouselItem);
        TimerUtil.waitUntilSeconds(2);
        final WebElement element = super.waitUntilVisibleByLocator(firstCarouselCard);
        TimerUtil.waitUntilSeconds(1);
        super.hoverElementWithActions(element);
        TimerUtil.waitUntilSeconds(1);
        super.waitUntilVisibleByLocator(By.xpath(firstCarouselBtn));
        super.clickWebElement(element.findElement(By.xpath(firstCarouselBtn)));
        TimerUtil.waitUntilSeconds(1);
        String countText = super.getText(sepetCount);
        if (!countText.equals("1"))
        {
            throw new IllegalArgumentException("Sepetim 1 degil");
        }
        super.clickWebElement(sepetim);
        TimerUtil.waitUntilSeconds(3);
        super.waitUntilVisibleByLocator(toplamTutarText);
        final String topText = super.getText(odenecekTutarTextCardTop);
        final String bottomText = super.getText(odenecekTutarTextCardToplam);
        final String replcedTutar = bottomText.replaceAll("[A-Z/ ]", "");
        if (!replcedTutar.equals(topText))
        {
            throw new IllegalArgumentException("Tutar uyusmadi");
        }
        super.clickWebElement(islemiYapBtn);
        super.waitUntilVisibleByLocator(musteriTeyidiModel);
        for (int i = 0; i < 4; i++)
        {
            super.clickWebElement(musteriTeyidiModelButton);
            TimerUtil.waitUntilSeconds(1);
        }
        TimerUtil.waitUntilSeconds(5);
        String countTextSepetAfterCheckout = super.getText(sepetCount);
        if (!countTextSepetAfterCheckout.equals("0"))
        {
            throw new IllegalArgumentException("Sepetim 0 degil");
        }
        System.out.println("Senaryo basarili...");
    }

}
