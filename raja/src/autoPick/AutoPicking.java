package autoPick;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;

import FDAccountOpening.FDAccountCreationMethods;
import adhocLimit.AdhocLimitMethods;
import blackListingCustomer.BlackListingCustomerMethods;

import customerCreation.CustomerCreationMethods;
import customerCreation.CustomerDeleteMethods;
import denominationVerification.DenominationVerificationMethods;
import drawingPower.DrawingPowerMethods;
import helper.AssertionHelper;
import helper.CacheHelper;
import helper.CommonMethodsForVerifications;
import helper.DateHelper;
import helper.DbHelper;
import helper.DropDownHelper;
import helper.ExecutionOfQuiresBeforeAnyTestCase;
import helper.FilloExcelDataGetter;
import helper.FindElement;
import helper.ReadPropertyFile;
import helper.ReportHelper;
import helper.RobotHelper;
import helper.SeleniumHelper;
import helper.SequencedProperties;
import helper.WaitingHelper;
import helper.WebTableHelper;
import lienNoting.LienNotingMethods;
import rdAccountOpening.RDAccountCreationMethods;
import savingsAccountCreation.SavingsAccountCreationMethods;
import tdCalculator.TdCalculatorMethods;
import testBase.TestBase;
public class AutoPicking extends TestBase{
	private static final Map<String, Operation> operation;
	static AssertionHelper assertionHelper = new AssertionHelper();		
	static SeleniumHelper seleniumHelper = new SeleniumHelper();	
	static DropDownHelper dropDownHelper = new DropDownHelper();
	FindElement findElement = new FindElement();
static WaitingHelper waitingHelper = new WaitingHelper();
	static DateHelper dateHelper = new DateHelper();
static	RobotHelper robotHelper = new RobotHelper();
static ReportHelper reportHelper = new ReportHelper();
static WebTableHelper webTableHelper = new WebTableHelper();
static DbHelper dbHelper = new DbHelper();
static CacheHelper cacheHelper = new CacheHelper();
static CustomerCreationMethods customerCreationMethods = new CustomerCreationMethods();
static DenominationVerificationMethods denominationVerificationMethods = new DenominationVerificationMethods();
static BlackListingCustomerMethods blackListingCustomerMethods = new BlackListingCustomerMethods();

static SavingsAccountCreationMethods savingsAccountCreationMethods = new SavingsAccountCreationMethods();
static TdCalculatorMethods tdCalculatorMethods = new TdCalculatorMethods();
static LienNotingMethods lienNotingMethods = new LienNotingMethods();
static AdhocLimitMethods adhocLimitMethods = new AdhocLimitMethods();
static DrawingPowerMethods drawingPowerMethods = new DrawingPowerMethods();
static Map<String,String> cacheMap=new HashMap<>();
static FilloExcelDataGetter filloExcelDataGetter = new  FilloExcelDataGetter();
static ExecutionOfQuiresBeforeAnyTestCase executionOfQuiresBeforeAnyTestCase = new ExecutionOfQuiresBeforeAnyTestCase();
static CommonMethodsForVerifications commonMethodsForVerifications = new CommonMethodsForVerifications();
static CustomerDeleteMethods  customerDeleteMethods  = new CustomerDeleteMethods ();
static FDAccountCreationMethods fDAccountCreationMethods = new FDAccountCreationMethods();
static RDAccountCreationMethods rDAccountCreationMethods = new RDAccountCreationMethods();
	File file=null;
	File file2=null;
	File file3=null;
	File directory;
	Properties table=null;
	static Properties table2=null;
	 HashMap<String,String>map=null;
	 static int i;
	 public HashMap loadingPropertyFile(String locatorFile,int i) {
			table=new SequencedProperties();
			table2=new SequencedProperties();
			try {	
			
				
			file = new File(relativePath()+locatorFile+"//locator.properties");
			file2 = new File(relativePath()+locatorFile+"//data"+i+".properties");
			FileInputStream fi;
			
				fi = new FileInputStream(file);
			
			FileInputStream	 fi2=new FileInputStream(file2);
				table.load(fi);
				table2.load(fi2);	
			map = new LinkedHashMap<String,String>();
			for (final Entry<Object, Object> entry : table.entrySet()) {
		       map.put((String) entry.getKey(), (String) entry.getValue());
			 }
			
	 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return map;}

	public void loadPropertyFile(String locatorFile) {
		
		
		try{
		String[] key=null;
		String key1=null;
		String key2=null;
		
		 directory = new File(relativePath()+locatorFile);
		 log.info("*******relativePath************"+relativePath());
		 log.info("*******locator file************"+directory);
		 List<String> propertyFiles = new ArrayList<>();
		 for(File file:directory.listFiles()) {
			 if(file.getName().endsWith(".properties")) {
				 propertyFiles.add(file.getName());
			 }
		 }
		
		 for(i=0;i<=propertyFiles.size()-2;i++) {
			
		map = loadingPropertyFile(locatorFile,i);
		
		for (String name: map.keySet()){

         key =name.toString().split("_");
        key1=key[0];
    
		key2=key[1];
          String value = map.get(key1+"_"+key2).toString();  
        log.info(key1+"_"+key2+"="+value);

if(!"submit".equalsIgnoreCase(key1)){
	createAction(value,key2,key1);
	}


           if("submit".equalsIgnoreCase(key1)&& i==propertyFiles.size()-2) { 
        	 
        	   seleniumHelper.clickElement(table.getProperty(key1+"_"+key2));
        	
           } 
		} 
		}}
		catch(Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	static {
	      final Map<String, Operation> players = new HashMap<>();
	      players.put("number", new Operation() {
	         
			@Override
			public void operation(String ele,String method,String labelName) {
				seleniumHelper.enterText(ele,table2.getProperty(labelName+"_"+method));
			
				
			}
	      });
	      
	      players.put("text", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					seleniumHelper.enterText(ele,table2.getProperty(labelName+"_"+method));
				
					
				}
		      });
		      
	      players.put("click", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					seleniumHelper.clickElement(ele);
					
					
				}
		      });
		      
		      players.put("checkbox", new Operation() {
			         
					@Override
					public void operation(String ele,String method,String labelName) {
						seleniumHelper.clickElement(ele);
						
						
					}
			      });
	      players.put("button", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					seleniumHelper.clickElement(ele);
				
					
				}
		      });
	      players.put("radio", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					seleniumHelper.clickElement(ele);
					
				}
		      });
	      
	      players.put("commonMethodsForVerifyAndSubmitModel", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					commonMethodsForVerifications.verifyAndSubmitModel(ele);
					
				}
		      });
	      players.put("getAccountNumberFromModelResponse", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					commonMethodsForVerifications.getAccountNumberModelResponse(ele, table2.getProperty(labelName+"_"+method), cacheMap);
					
				}
		      });
	      players.put("label", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					assertionHelper.verifyTextEquals(ele,table2.getProperty(labelName+"_"+method) );
					
				}
		      });
	      players.put("select", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					 dropDownHelper.SelectUsingVisibleText(ele, table2.getProperty(labelName+"_"+method));
					
					
				}
		      });
	      players.put("selectByElement", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					 dropDownHelper.selectByElement(ele, table2.getProperty(labelName+"_"+method));
					
					
				}
		      });
	      players.put("dropdownByID", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					 dropDownHelper.SelectUsingIndex(ele, Integer.parseInt( table2.getProperty(labelName+"_"+method).trim()));
						
					
				}
		      });
	      
	      players.put("getSelectedValueFromDropdown", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					 dropDownHelper.getSelectedValue(ele);
						
					
				}
		      });
	      players.put("calendar", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					dateHelper.enterStaticDateForCBSCalender(ele);	
					
					
				}
		      });
	      players.put("Webtable", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					  webTableHelper.validateTableValuesUsingExternalFile(ele, table2.getProperty(labelName+"_"+method));
					
				}
		      });
	      
	      players.put("file", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					seleniumHelper.clickElement(ele);
					robotHelper.fileUploadUsingRobot(table2.getProperty(labelName+"_"+method));
				}
		      });
	      
	      players.put("VerifyListofUIWithDb", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					
					dbHelper.verifyListOfUIElementsUsingDb(ele,table2.getProperty(labelName+"_"+method));
					
				}
		      });
	      
	      players.put("validateColumnsOfTwoTables", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					
					dbHelper.validateColumnsOfTwoTables(ele,table2.getProperty(labelName+"_"+method));
					
				}
		      });
	      players.put("CompareTextWithDB", new Operation() {

	    /* parameter---> ele needs a query
	     * parameter---> second parameter needs the expected result of a column
	     */
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					
					dbHelper.compareResultUsingDb(ele, table2.getProperty(labelName+"_"+method));
					
				}
		      });
	      
	      players.put("compareExcelResultUsingDb", new Operation() {

	  	    /* parameter---> ele needs a query
	  	     * parameter---> second parameter needs the expected result of a column
	  	     */
	  		         
	  				@Override
	  				public void operation(String ele,String method,String labelName) {
	  					
	  					dbHelper.compareExcelResultUsingDb(ele, table2.getProperty(labelName+"_"+method),labelName);
	  					
	  				}
	  		      });
	      players.put("verifyUiAmountWithDb", new Operation() {

	  	    /* parameter---> ele needs a query
	  	     * parameter---> second parameter needs the expected result of a column
	  	     */
	  		         
	  				@Override
	  				public void operation(String ele,String method,String labelName) {
	  					
	  					dbHelper.verifyUiAmountWithDb(ele, table2.getProperty(labelName+"_"+method));
	  					
	  				}
	  		      });
	  	      
	      players.put("compareSumOfTableIntUsingDb", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					
					dbHelper.compareIntUsingDb( webTableHelper.getSumOfaWebElementList(ele),table2.getProperty(labelName+"_"+method));
					
				}
		      });
	      
	      
	      players.put("verifyUiTextWithDb", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					
					dbHelper.verifyUiTextWithDb(ele, table2.getProperty(labelName+"_"+method));
					
				}
		      });
	      players.put("inputUsingDb", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					
					dbHelper.inputUsingDb(ele, table2.getProperty(labelName+"_"+method));
				}
		      });
	      
	    
	      
	      players.put("verifyUiDateWithDB", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					
					dbHelper.verifyUiDateWithDB(ele, table2.getProperty(labelName+"_"+method));
				}
		      });
	      
	      players.put("verifyCalendarDate", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					
					assertionHelper.verifyCalendarText(ele, dateHelper.getSystemDate("yyyy/mm/dd"));
					
				}
		      });
	      players.put("verifyDropDownText", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					
					assertionHelper.verifyDropdownText(ele,  table2.getProperty(labelName+"_"+method));
					
				}
		      });
	      players.put("verifyCalendarTextUsingExcel", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					
					assertionHelper.verifyCalendarTextUsingExcel(ele,  table2.getProperty(labelName+"_"+method),labelName);
					
				}
		      });
	      
	      players.put("verifyTitle", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					
					seleniumHelper.verifyTitle(ele);
					
				}
		      });
	      players.put("executeQuriesFromExcel", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					
					executionOfQuiresBeforeAnyTestCase.executeQuriesFromExcel(ele, table2.getProperty(labelName+"_"+method));
					
				}
		      });
	      players.put("setCache", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					cacheHelper.setCache(ele, table2.getProperty(labelName+"_"+method),cacheMap);
					
					
				}
		      });
	      players.put("setCacheUsingString", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					cacheHelper.setCacheWithaString(ele, table2.getProperty(labelName+"_"+method),cacheMap);
					
					
				}
		      });
	      
	      players.put("inputUsingCache", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					seleniumHelper.enterText(ele, cacheHelper.getCache(table2.getProperty(labelName+"_"+method),cacheMap));
					
					
				}
		      });
	      
	      
	      players.put("verifyTextUsingCache", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					assertionHelper.verifyTextEquals(ele, cacheHelper.getCache(table2.getProperty(labelName+"_"+method),cacheMap));
					
					
				}
		      });
	      
	      players.put("verifyTextEquals", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					assertionHelper.verifyTextEquals(ele, table2.getProperty(labelName+"_"+method));
					
					
				}
		      });
	     
	      players.put("Tab", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					seleniumHelper.clickElement(ele);
					
					
				}
		      });  
	     // getCustomerCreationModelResponse
	      
	      players.put("getCustomerCreationModelResponse", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					customerCreationMethods.getCustomerCreationModelResponse(ele,cacheMap);
					
				}
		      }); 
	      players.put("custMasterAndUIVerification", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					customerCreationMethods.custMasterAndUIVerification(ele,  table2.getProperty(labelName+"_"+method), cacheMap);
					
				}
		      }); 
	      players.put("getCustomerAuthorizationModelResponse", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					customerCreationMethods.getCustomerAuthorizationModelResponse(ele);
					
				}
		      }); 
	      
	      players.put("tableVerificationsInCustomerCreation", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					customerCreationMethods.tableVerificationsInCustomerCreation(cacheMap,ele);
					
				}
		      }); 
	      players.put("customerDeleteDataVerification", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					customerDeleteMethods.customerDeleteDataVerification(ele);
					
				}
		      }); 
	      
	      players.put("customerDeleteTableVerifications", new Operation() {
		         
	  				@Override
	  				public void operation(String ele,String method,String labelName) {
	  					customerDeleteMethods.customerDeleteTableVerifications(ele,table2.getProperty(labelName+"_"+method));
	  					
	  				}
	  		      }); 
	      
	      
	      players.put("getSavingsAccountNumber", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					savingsAccountCreationMethods.getSavingsAccountNumberModelResponse(ele,cacheMap);
					
				}
		      }); 
	      
	      players.put("tableVerificationOfSBAccountOnSubmit", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					savingsAccountCreationMethods.tableVerificationOfSBAccountOnSubmit(ele, cacheMap);
					
				}
		      }); 
	      players.put("accountMasterAndUIVerification", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					savingsAccountCreationMethods.accountMasterAndUIVerification(ele,cacheMap);
					
				}
		      }); 
	            
	      players.put("waitForAngular", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					waitingHelper.waitForElementVisibleUtillAngularDone(By.xpath(ele), 3, 10);
					
				}
		      }); 
	      players.put("waitFor", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					waitingHelper.sleep(Integer.parseInt(ele));
					
				}
		      }); 
	      
	     
	      players.put("transferdenomTVerification", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					denominationVerificationMethods.denomTransferVerification(ele,table2.getProperty(labelName+"_"+method),cacheHelper,cacheMap);	
				}
		      }); 
	      players.put("compareTransferDenomAmountWithDB", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					dbHelper.compareResultUsingDb(cacheHelper.getCache(ele,cacheMap),table2.getProperty(labelName+"_"+method));	
				}
		      }); 
	      
	 
	     
	      players.put("customerInfoVerificationInBlackListingCustomer", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					blackListingCustomerMethods.customerInfoVerificationInBlackListingCustomer(ele);
				}
		      }); 
	      
	      players.put("blackListingTableVerification", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					blackListingCustomerMethods.blackListingTableVerification(ele,table2.getProperty(labelName+"_"+method));
				}
		      }); 
	      players.put("tableVerificaitonOnSubmitInBlackListing", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					blackListingCustomerMethods.tableVerificaitonOnSubmitInBlackListing(ele,table2.getProperty(labelName+"_"+method));
				}
		      }); 
	      players.put("commonSimpleFDCalculator", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					tdCalculatorMethods.commonSimpleFDCalculator(ele, table2.getProperty(labelName+"_"+method));
				}
		      }); 
		      players.put("lienNotingEntry", new Operation() {
			         
					@Override
					public void operation(String ele,String method,String labelName) {
					
						lienNotingMethods.lienNotingEntry(ele);
					}
			      }); 
	      players.put("lienAmountVerification", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
				
					lienNotingMethods.lienAmountVerification(ele, table2.getProperty(labelName+"_"+method));
				}
		      }); 
	      
	      players.put("customerInfoInLien", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
				
					lienNotingMethods.customerInfoInLien(ele, table2.getProperty(labelName+"_"+method));
				}
		      }); 
	      
	      players.put("depositAccValidationInLien", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
				
					lienNotingMethods.depositAccValidationInLien(ele, table2.getProperty(labelName+"_"+method));
				}
		      }); 
	      players.put("lienAmountVerification", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
				
					lienNotingMethods.lienAmountVerification(ele, table2.getProperty(labelName+"_"+method));
				}
		      }); 
		      players.put("tableVerificationOnSubmitInLienNoting", new Operation() {
			         
					@Override
					public void operation(String ele,String method,String labelName) {
					
						lienNotingMethods.tableVerificationOnSubmitInLienNoting(ele);
					}
			      });
		      players.put("lienNotingAuthScreenVerifications", new Operation() {
			         
					@Override
					public void operation(String ele,String method,String labelName) {
					
						lienNotingMethods.lienNotingAuthScreenVerifications(ele,table2.getProperty(labelName+"_"+method));
					}
			      });
		      players.put("tableVerificationOnSunbitInLienAuth", new Operation() {
				         
						@Override
						public void operation(String ele,String method,String labelName) {
						
							lienNotingMethods.tableVerificationOnSunbitInLienAuth(ele,table2.getProperty(labelName+"_"+method));
						}
				      });
	      players.put("adhocLimitEntry", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					adhocLimitMethods.adhocLimitEntry(ele);
				}
		      }); 
	      players.put("validateAdhocCustomerInfo", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					adhocLimitMethods.validateCustomerInfo(ele);
				}
		      }); 
	      players.put("verifyExpiryDateInAdhocLimit", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					adhocLimitMethods.verifyExpiryDateInAdhocLimit(ele);
				}
		      });
	      
	      players.put("adhocEntryTableVerfications", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					adhocLimitMethods.adhocEntryTableVerfications(ele, table2.getProperty(labelName+"_"+method));
				}
		      });

	      players.put("validateAdhocAuthScreenData", new Operation() {
		         
	 				@Override
	 				public void operation(String ele,String method,String labelName) {
	 					adhocLimitMethods.validateAdhocAuthScreenData(ele);
	 				}
	 		      });
	      
	      players.put("validateAdhocAuthTables", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					adhocLimitMethods.validateAdhocAuthTables(ele, table2.getProperty(labelName+"_"+method));
				}
		      });
	      players.put("registerDPSubmission", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					drawingPowerMethods.registerDPSubmission(ele);
				}
		      }); 
	      players.put("verifyCustomerDetailsinDrawingPowerScreen", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					drawingPowerMethods.verifyCustomerDetailsinDrawingPowerScreen(ele,labelName);
				}
		      }); 
	      players.put("validateMarginCalculationInDrawingPowerScreen", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					drawingPowerMethods.validateMarginCalculation(ele,  table2.getProperty(labelName+"_"+method));
				}
		      }); 
	      players.put("validateTotalDpArriviedAndAllowed", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					drawingPowerMethods.validateTotalDpArriviedAndAllowed(ele, labelName);
				}
		      }); 
	      
	      players.put("verifyDpEntryAuthScreen", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					drawingPowerMethods.verifyDpEntryAuthScreen(ele,table2.getProperty(labelName+"_"+method));
				}
		      }); 
	      
	      players.put("enterTextUsingExcelValue", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					seleniumHelper.enterTextUsinfExcelValue(ele, table2.getProperty(labelName+"_"+method), labelName.trim());
				}
		      }); 
	      players.put("submitVerificationsInDpEntry", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					drawingPowerMethods.submitVerificationsInDpEntry(ele,  table2.getProperty(labelName+"_"+method));
				}
		      }); 
	      
	      players.put("tableVerificationInDpRegisterAuth", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					drawingPowerMethods.authTableValidations(ele, table2.getProperty(labelName+"_"+method));
				}
		      });
	      players.put("tableVerificationInDpRegisterReject", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					drawingPowerMethods.rejectTableVerifications(ele);
				}
		      });
	      players.put("tableVerificationInDpRegisterCancel", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					drawingPowerMethods.cancelTableVerifications(ele);
				}
		      });
	      
	      players.put("getDataFromaColumnOfAnExcellSheet", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(ele, table2.getProperty(labelName+"_"+method));
				}
		      });
	      players.put("searchMenu", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					seleniumHelper.searchMenu(ele);
				}
		      });
	      
	        
	      players.put("entryFDCreation", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					fDAccountCreationMethods.entryFDCreation(ele);
				}
		      });
	      
	      players.put("verifyMaturityDateInFDCreation", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					fDAccountCreationMethods.verifyMaturityDate(ele);
				}
		      });
	      
	      players.put("maturityAmountVerificationForSimpleInt", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					fDAccountCreationMethods.maturityAmountVerificationForSimpleInt(ele);
				}
		      });
	      
	      players.put("verifyCustCategoryInFDOpening", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					fDAccountCreationMethods.verifyCustCategoryInFDOpening(ele);
				}
		      });
	      players.put("fdAccountOpeningTablelevelVerification", new Operation() {
		         
	  				@Override
	  				public void operation(String ele,String method,String labelName) {
	  					fDAccountCreationMethods.fdAccountOpeningTablelevelVerification(ele, table2.getProperty(labelName+"_"+method),labelName, cacheMap);
	  				}
	  		      });
	      players.put("fdAuthUIVerifications", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					fDAccountCreationMethods.fdAuthUIVerifications(ele, cacheMap,table2.getProperty(labelName+"_"+method));
				}
		      });
		      players.put("RDAccountEntry", new Operation() {
			         
					@Override
					public void operation(String ele,String method,String labelName) {
						rDAccountCreationMethods.RDAccountEntry(ele);
					}
			      });
	      players.put("verifyMaturityDateInRDAccountOpening", new Operation() {
		         
	  				@Override
	  				public void operation(String ele,String method,String labelName) {
	  					rDAccountCreationMethods.verifyMaturityDateInRDAccountOpening(ele);
	  				}
	  		      });
	      
	      players.put("RDAuthUIVerifications", new Operation() {
		         
				@Override
				public void operation(String ele,String method,String labelName) {
					rDAccountCreationMethods.RDAuthUIVerifications(ele, cacheMap, table2.getProperty(labelName+"_"+method));
				}
		      });
	      operation = Collections.unmodifiableMap(players);
}
	   
	   public void createAction(String ele,String method,String labelName) {
		   Operation command = operation.get(method);

		      if (command == null) {
		       log.info("Invalid action type: "
		            + ele);
		      }

		       command.operation( ele, method, labelName);
		   }
}
