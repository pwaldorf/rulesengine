package com.pw.workflowengine.sampleworkflow;

import java.io.Serializable;
import java.util.Map;

import org.mvel2.MVEL;

import com.pw.workflowengine.stepbuilder.model.MvelExpression;

public class TestPerfMvel {

    private MvelExpression mvelExpression;
    private Serializable compiledExpression;

    public TestPerfMvel() {
         // Construct MVEL script using StringBuilder
         StringBuilder script = new StringBuilder();
         script.append("if (\"GTM\".equalsIgnoreCase(SourceSystemName)) { ")
               .append("    if (\"CLS\".equalsIgnoreCase(BuyCurrency) || \"CLS\".equalsIgnoreCase(SellCurrency)) { ")
               .append("        statusCode = (String)StatusCode1; ")
               .append("        statusCode = statusCode.endsWith(\"/\") ? statusCode.substring(1, statusCode.length() - 1) : statusCode; ")
               .append("        if (\"SOKS\".equalsIgnoreCase(statusCode)) { ")
               .append("            if (SenderBic.length() == 12) { ")
               .append("                String swiftBic = SenderBic.toString; ")
               .append("            } else { ")
               .append("             String swiftBic = SenderBic.toString; ")
               .append("            } ")
               .append("            SsbOfficeCodeMap = SenderBic; ")
               .append("        } ")
               .append("    } else { ")
               .append("        Status = \"IGNORE\"; ")
               .append("        ReasonCode = \"Status code can be ignored\"; ")
               .append("    } ")
               .append("}");

        compiledExpression = MVEL.compileExpression(script.toString());
    }

    public void execute(Map<String, Object> context, Map<String, Object> vars) {
        MVEL.executeExpression(compiledExpression, context, vars);
    }

}
