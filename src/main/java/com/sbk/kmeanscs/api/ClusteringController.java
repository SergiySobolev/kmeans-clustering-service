package com.sbk.kmeanscs.api;

import com.sbk.kmeanscs.algo.InboundDataGenerator;
import com.sbk.kmeanscs.api.request.ClusteringRequest;
import com.sbk.kmeanscs.api.request.GenerateRequest;
import com.sbk.kmeanscs.api.response.ClusteringResponse;
import com.sbk.kmeanscs.api.response.GenerateResponse;
import com.sbk.kmeanscs.service.ClusteringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClusteringController {

    private final ClusteringService clusteringService;

    public ClusteringController(ClusteringService clusteringService) {
        this.clusteringService = clusteringService;
    }

    @GetMapping(path = "/clusterdata")
    public ClusteringResponse clusterData(@RequestBody ClusteringRequest clusteringRequest) {
        var res = clusteringService.clusterData(clusteringRequest.data, clusteringRequest.clusterNum, clusteringRequest.type);
        return new ClusteringResponse(res);
    }

    @GetMapping(path = "/generatedata")
    public GenerateResponse generateData(@RequestBody GenerateRequest generateRequest) {
        var inboundDataGenerator = new InboundDataGenerator();
        var generatedData = inboundDataGenerator.generate(generateRequest.clusterNum, generateRequest.bounds);
        return new GenerateResponse(generatedData);
    }

}
