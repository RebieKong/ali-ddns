package com.rebiekong.aliyun.tools.service;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.AddDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.ProtocolType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DnsService {

    @Autowired
    private IAcsClient client;

    public void updateDDns(String domain, String rr, String value) throws ClientException {
        String recordId = getRecordId(domain, rr);
        if (recordId == null) {
            addRecord(domain, rr, value);
        } else {
            updateRecord(recordId, rr, value);
        }
    }

    private void addRecord(String domain, String rr, String value) throws ClientException {
        AddDomainRecordRequest request = new AddDomainRecordRequest();
        request.setSysProtocol(ProtocolType.HTTPS);
        request.setDomainName(domain);
        request.setRR(rr);
        request.setType("A");
        request.setValue(value);
        client.doAction(request);
    }

    private void updateRecord(String recordId, String rr, String value) throws ClientException {
        UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
        request.setSysProtocol(ProtocolType.HTTPS);
        request.setRecordId(recordId);
        request.setRR(rr);
        request.setType("A");
        request.setValue(value);
        client.doAction(request);
    }

    private String getRecordId(String domain, String rr) throws ClientException {
        DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
        request.setSysProtocol(ProtocolType.HTTPS);
        request.setAcceptFormat(FormatType.JSON);
        request.setDomainName(domain);
        request.setKeyWord(rr);
        request.setType("A");
        DescribeDomainRecordsResponse r = client.getAcsResponse(request);
        List<DescribeDomainRecordsResponse.Record> v = r.getDomainRecords().stream().filter(a -> a.getRR().equals(rr)).collect(Collectors.toList());
        if (v.size() > 0) {
            return v.get(0).getRecordId();
        } else {
            return null;
        }
    }
}