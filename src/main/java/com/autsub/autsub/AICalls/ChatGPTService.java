package com.autsub.autsub.AICalls;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.autsub.autsub.CompanyPlan.CompanyPlan;
import com.autsub.autsub.Exception.APIcallFailedException;

import java.util.Arrays;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.*;

@Service
public class ChatGPTService {
    
    private String apiKey = "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";

    private String apiUrl = "https://api.openai.com/v1/chat/completions";

    private final RestTemplate restTemplate = new RestTemplate();

   
    public String getChatGPTResponse(String userMessage) {
        String modelName = "gpt-3.5-turbo";
       String completeMes = userMessage + "# Churn Model Insights\n" + //
                      "\n" + //
                      "## Model Overview\n" + //
                      "- **Model Type:** Logistic Regression\n" + //
                      "- **Performance Metrics:**\n" + //
                      "  - **Accuracy:** 73.29%\n" + //
                      "  - **Precision:** [Calculate Precision]\n" + //
                      "  - **Recall:** [Calculate Recall]\n" + //
                      "  - **F1-Score:** [Calculate F1-Score]\n" + //
                      "  - **AUC-ROC:** [Calculate AUC-ROC]\n" + //
                      "\n" + //
                      "## Feature Importance\n" + //
                      "### SHAP Values (Top 5 Features)\n" + //
                      "| Feature           |   Average SHAP Value |\n" + //
                      "|:------------------|---------------------:|\n" + //
                      "| tenure            |             1.15693  |\n" + //
                      "| Contract_Two year |             0.636434 |\n" + //
                      "| MonthlyCharges    |             0.54864  |\n" + //
                      "| TotalCharges      |             0.457646 |\n" + //
                      "| Contract_One year |             0.353061 |\n" + //
                      "\n" + //
                      "### Model Coefficients\n" + //
                      "| Feature                        |   Coefficient |\n" + //
                      "|:-------------------------------|--------------:|\n" + //
                      "| Contract_Two year              |     -1.82186  |\n" + //
                      "| Contract_One year              |     -1.06395  |\n" + //
                      "| PaymentMethod_Electronic check |      0.460708 |\n" + //
                      "| PaymentMethod_Mailed check     |     -0.204498 |\n" + //
                      "| Discrepancy_Ratio              |     -0.151245 |\n" + //
                      "\n" + //
                      "## Summary Statistics\n" + //
                      "### Numerical Features\n" + //
                      "| Feature           |   count |       Mean |      Std Dev |       Min |        25% |   Median |        75% |        Max |\n" + //
                      "|:------------------|--------:|-----------:|-------------:|----------:|-----------:|---------:|-----------:|-----------:|\n" + //
                      "| tenure            |    7032 |   32.4218  |   24.5453    |  1        |   9        |    29    |   55       |   72       |\n" + //
                      "| MonthlyCharges    |    7032 |   64.7982  |   30.086     | 18.25     |  35.5875   |    70.35 |   89.8625  |  118.75    |\n" + //
                      "| TotalCharges      |    7032 | 2283.3     | 2266.77      | 18.8      | 401.45     |  1397.47 | 3794.74    | 8684.8     |\n" + //
                      "| Discrepancy_Ratio |    7032 |    1.00027 |    0.0511586 |  0.689356 |   0.979546 |     1    |    1.01956 |    1.57345 |\n" + //
                      "\n" + //
                      "### Categorical Features\n" + //
                      "| Feature                               |   True_Count |   False_Count |\n" + //
                      "|:--------------------------------------|-------------:|--------------:|\n" + //
                      "| Contract_One year                     |         1472 |          5560 |\n" + //
                      "| Contract_Two year                     |         1685 |          5347 |\n" + //
                      "| PaymentMethod_Credit card (automatic) |         1521 |          5511 |\n" + //
                      "| PaymentMethod_Electronic check        |         2365 |          4667 |\n" + //
                      "| PaymentMethod_Mailed check            |         1604 |          5428 |\n" + //
                      "\n" + //
                      "## Correlation with Churn\n" + //
                      "| Feature                               |   Correlation_with_Churn |\n" + //
                      "|:--------------------------------------|-------------------------:|\n" + //
                      "| tenure                                |               -0.354049  |\n" + //
                      "| MonthlyCharges                        |                0.192858  |\n" + //
                      "| TotalCharges                          |               -0.199484  |\n" + //
                      "| Discrepancy_Ratio                     |               -0.0119794 |\n" + //
                      "| Contract_One year                     |               -0.178225  |\n" + //
                      "| Contract_Two year                     |               -0.301552  |\n" + //
                      "| PaymentMethod_Credit card (automatic) |               -0.134687  |\n" + //
                      "| PaymentMethod_Electronic check        |                0.301455  |\n" + //
                      "| PaymentMethod_Mailed check            |               -0.0907728 |\n" + //
                      "\n" + //
                      "## Customer Segmentation\n" + //
                      "### By Contract Type\n" + //
                      "| Contract_Type   |   Churn_Rate |   Count |\n" + //
                      "|:----------------|-------------:|--------:|\n" + //
                      "| Month-to-Month  |    0.427097  |    3875 |\n" + //
                      "| Two Year        |    0.0284866 |    1685 |\n" + //
                      "| One Year        |    0.112772  |    1472 |\n" + //
                      "\n" + //
                      "## Temporal Trends\n" + //
                      "No temporal data available.\n" + //
                      "\n" + //
                      "## Customer Feedback Insights\n" + //
                      "- **Common Complaints:** [List common complaints]\n" + //
                      "- **Positive Feedback:** [List positive feedback]\n" + //
                      "- **Suggestions for Improvement:** [List suggestions]";

    
        ChatRequest request = new ChatRequest(
            modelName,  
            Arrays.asList(new ChatRequest.Message("system", "You are an AI assistant integrated into a subscription management system specialized in permit engineering. Your primary task is to analyze business statistics stored in our database and provide insightful recommendations to business owners. Your focus includes churn rate analysis and other key performance metrics related to permits.\n" + //
                                "\n" + //
                                "Context:\n" + //
                                "\n" + //
                                "The user is a business owner seeking to reduce churn and optimize their subscription model.\n" + //
                                "They may inquire about churn reduction strategies, factors contributing to churn, pricing adjustments, contract types, and methods to improve customer satisfaction and retention.\n" + //
                                "You have access to a permit report with:\n" + //
                                "Churn Model Insights (Logistic regression model with SHAP values and coefficients)\n" + //
                                "Feature Importance (e.g., tenure, contract type, payment method, discrepancy ratio)\n" + //
                                "Summary Statistics (numerical and categorical breakdowns)\n" + //
                                "Correlation with Churn (how various features impact churn)\n" + //
                                "Customer Segmentation (churn rates by contract type)\n" + //
                                "Customer Feedback Insights (common complaints, positive feedback, suggestions)\n" + //
                                "Response Requirements:\n" + //
                                "\n" + //
                                "Use only HTML tags (such as <p>, <ul>, <li>, <strong>) for your responses.\n" + //
                                "Do not include <body>, <head>, or any CSS in your responses.\n" + //
                                "Reference the permit report data and provide actionable recommendations for improving churn and overall performance.\n" + //
                                "Support recommendations with relevant statistics or insights (e.g., SHAP values, correlation data, segmentation findings).\n" + //
                                "Keep responses concise, professional, and directly related to reducing churn and optimizing subscription performance.\n" + //
                                "Example Interaction:\n" + //
                                "\n" + //
                                "User:\n" + //
                                "How can I lower my churn rate?\n" + //
                                "\n" + //
                                "AI Response (HTML without body/head/CSS):\n" + //
                                "\n" + //
                                "vbnet\n" + //
                                "Copy\n" + //
                                "Edit\n" + //
                                "<p>Based on the permit report, the highest contributors to churn include short-tenure customers and those on month-to-month contracts. Customers using electronic checks also have a higher churn correlation (+0.301). To reduce churn, consider:</p>\n" + //
                                "<ul>\n" + //
                                "  <li>Offering incentives for longer-term contracts, such as discounts for annual subscriptions.</li>\n" + //
                                "  <li>Encouraging customers to switch from electronic checks to automatic credit card payments.</li>\n" + //
                                "  <li>Addressing common customer complaints to enhance overall satisfaction and retention.</li>\n" + //
                                "</ul>\n" + //
                                "Adhere to these instructions for every response you provide."),
             new ChatRequest.Message("user", completeMes)),  
            100 
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<ChatResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, ChatResponse.class);
            
            return response.getBody().getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }



    public void sendPlanDataToChatGPT(List<CompanyPlan> companyPlans){
        String modelName = "gpt-3.5-turbo";
        List<CompanyPlan> content = companyPlans;

        
        ChatRequest request = new ChatRequest(
            modelName,  
            Arrays.asList(new ChatRequest.Message("user", companyPlans)),  
            100 
        );

      // request.setMessages(new ChatRequest.Message("user", content));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<ChatResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, ChatResponse.class);
            
             response.getBody().getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
             throw new APIcallFailedException(e.getMessage()); 
        }

    }
}
