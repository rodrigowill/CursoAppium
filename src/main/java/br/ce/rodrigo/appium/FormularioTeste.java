package br.ce.rodrigo.appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class FormularioTeste {
	
	private AndroidDriver<MobileElement> driver;
	
	@Before
	private void inicializarAppium() throws MalformedURLException {
		
		// Inicializa App
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability("platformName", "Android");
	    desiredCapabilities.setCapability("deviceName", "emulator-5554");
	    desiredCapabilities.setCapability("automationName", "uiautomator2");
	    desiredCapabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\Rodrigo\\udemy-appium-workspace\\CursoAppium\\src\\main\\resources\\CTAppium_1_1.apk");
	    driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    // Selecionar formulário
	    driver.findElement(By.xpath("//*[@text='Formulário']")).click();
	}
	
	@After
	private void tearDown() {
		driver.quit();
	}
	
	@Test
	public void devePreencherCampoTexto() throws MalformedURLException {
	    
	    MobileElement campoNome = driver.findElement(MobileBy.AccessibilityId("nome"));
	    campoNome.sendKeys("Rodrigo");
	    String texto = campoNome.getText();
	    Assert.assertEquals("Rodrigo", texto);
	}
	
	@Test
	public void deveInteragirCombo() throws MalformedURLException {
		
	    // Clicar no combo
	    driver.findElement(MobileBy.AccessibilityId("console")).click();
	    
	    // Selecionar opção desejada
	    driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Nintendo Switch']")).click();;
	    
	    // Verificar opção selecionada
	    String texto = driver.findElement(By.xpath("//android.widget.Spinner/android.widget.TextView")).getText();
	    Assert.assertEquals("Nintendo Switch", texto);
	}
	
	@Test
	public void deveInteragirSwitchCheckBox() throws MalformedURLException {
	    
	    // Verificar o status dos elementos
	    MobileElement check = driver.findElement(By.className("android.widget.CheckBox"));
	    MobileElement switc = driver.findElement(MobileBy.AccessibilityId("switch"));
	    Assert.assertTrue(check.getAttribute("checked").contentEquals("false"));
	    Assert.assertTrue(switc.getAttribute("checked").contentEquals("true"));
	    
	    // Clicar nos elementos
	    check.click();
	    switc.click();
	    
	    // Verificar estados alterados
	    Assert.assertFalse(check.getAttribute("checked").contentEquals("false"));
	    Assert.assertFalse(switc.getAttribute("checked").contentEquals("true"));
	}
	
	@Test
	public void deveRealizarCadastroComSucesso() throws MalformedURLException {
		
	    // Preencher nome
	    MobileElement campoNome = driver.findElement(MobileBy.AccessibilityId("nome"));
	    campoNome.sendKeys("Rodrigo");
	    
	    // Selecionar opção no compo
	    driver.findElement(MobileBy.AccessibilityId("console")).click();
	    driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Nintendo Switch']")).click();;
	    
	    // Verificar o status dos elementos
	    driver.findElement(By.className("android.widget.CheckBox")).click();;
	    driver.findElement(MobileBy.AccessibilityId("switch")).click();;

	    // Clicar no botão salvar
	    driver.findElement(By.xpath("//*[@text='SALVAR']")).click();
	    
	    // Verificar informações de cadastro
	    Assert.assertEquals("Nome: Rodrigo", driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Nome:')]")).getText());
	    Assert.assertEquals("Console: switch", driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Console:')]")).getText());
	    Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Switch:')]")).getText().endsWith("Off"));
	    Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Checkbox:')]")).getText().endsWith("Marcado"));
	    
	}

}
