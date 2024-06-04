package com.aesctzn.microservices.temporal.bookreservation.infrastructure.temporal.namespaces;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.protobuf.util.Durations;

import io.temporal.api.enums.v1.ArchivalState;
import io.temporal.api.namespace.v1.NamespaceConfig;
import io.temporal.api.workflowservice.v1.DeprecateNamespaceRequest;
import io.temporal.api.workflowservice.v1.DeprecateNamespaceResponse;
import io.temporal.api.workflowservice.v1.DescribeNamespaceRequest;
import io.temporal.api.workflowservice.v1.DescribeNamespaceResponse;
import io.temporal.api.workflowservice.v1.ListNamespacesRequest;
import io.temporal.api.workflowservice.v1.ListNamespacesResponse;
import io.temporal.api.workflowservice.v1.RegisterNamespaceRequest;
import io.temporal.api.workflowservice.v1.RegisterNamespaceResponse;
import io.temporal.api.workflowservice.v1.UpdateNamespaceRequest;
import io.temporal.api.workflowservice.v1.UpdateNamespaceResponse;
import io.temporal.serviceclient.WorkflowServiceStubs;

@Service
public class NamespaceManagement {

    @Autowired
    private WorkflowServiceStubs serviceStubs;

    public RegisterNamespaceResponse createNamespace(NamespaceRegisterDto register) {

        RegisterNamespaceRequest request =
                RegisterNamespaceRequest.newBuilder()
                        .setNamespace(register.getName())
                        .setDescription(register.getDescription())
                        .setOwnerEmail(register.getEmailOwner())
                        .setWorkflowExecutionRetentionPeriod(Durations.fromDays(register.getRetention()))
                        .build();

        //    RegisterNamespaceRequest request =
        //            RegisterNamespaceRequest.newBuilder()
        //                    .setNamespace("Archival-Test")
        //                    .setIsGlobalNamespace(true)
        //                    .setHistoryArchivalState(ArchivalState.ARCHIVAL_STATE_ENABLED)
        //                    .setVisibilityArchivalState(ArchivalState.ARCHIVAL_STATE_ENABLED)
        //                    .setHistoryArchivalUri("s3://9cuz9igx-s3-archival")
        //                    .setVisibilityArchivalUri("s3://9cuz9igx-s3-archival")
        //                    .setWorkflowExecutionRetentionPeriod(Duration.newBuilder().setSeconds(86400).build())
        //                    .build();


        return serviceStubs.blockingStub().registerNamespace(request);
    }

    public UpdateNamespaceResponse updateNamespace(NamespaceRegisterDto register) {

        UpdateNamespaceRequest request = UpdateNamespaceRequest.newBuilder()
                .setNamespace(register.getName())
                .setConfig(NamespaceConfig.newBuilder()
                        .setVisibilityArchivalState(ArchivalState.ARCHIVAL_STATE_ENABLED)
                        .setHistoryArchivalState(ArchivalState.ARCHIVAL_STATE_ENABLED)
                        .setHistoryArchivalUri(register.getVisibilityArchivalURI())
                        .setVisibilityArchivalUri(register.getVisibilityArchivalURI())
                        .build())
                .build();


        return serviceStubs.blockingStub().updateNamespace(request);
    }

    public DescribeNamespaceResponse describeNamespace(String name) {
        DescribeNamespaceRequest descNamespace = DescribeNamespaceRequest.newBuilder()
                .setNamespace(name)
                .build();
        return serviceStubs.blockingStub().describeNamespace(descNamespace);
    }

    public DeprecateNamespaceResponse deprecateNamespace(String name) {
        DeprecateNamespaceRequest request = DeprecateNamespaceRequest.newBuilder()
                .setNamespace(name)
                .build();
        return serviceStubs.blockingStub().deprecateNamespace(request);
    }


    public ListNamespacesResponse getListNamespaces(){
        ListNamespacesRequest listNamespaces = ListNamespacesRequest.newBuilder().build();
        return serviceStubs.blockingStub().listNamespaces(listNamespaces);
    }
}
