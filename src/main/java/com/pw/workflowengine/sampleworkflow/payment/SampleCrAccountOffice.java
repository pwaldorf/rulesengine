package com.pw.workflowengine.sampleworkflow.payment;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.pw.workflowengine.workflow.StepGroup;
import com.pw.workflowengine.workflow.impl.JavaAction;

public class SampleCrAccountOffice extends JavaAction<String, Object> {

    @Override
    public boolean execute(Map<String, Object> context, Map<String, StepGroup> stepGroups) {

        // String t24CompanyCd = null;
        // if (StringUtils.isNotBlank(context.get("CrAccountOffice").toString())) {
        //     String originalCrAcctOffice = context.get("CrAccountOffice").toString();
        //     t24CompanyCd = GBMUtiltiy.getCompanyCdByOfficeMnemonic(originalCrAcctOffice);
        //     context.put("OriginalCrAcctOffice", originalCrAcctOffice);
        // }
        // return t24CompanyCd;
        return true;
    }

}
