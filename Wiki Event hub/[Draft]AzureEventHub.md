[1. **Architecture**](#1-architecture)

 

&nbsp;&nbsp;[1.1. Owner](#11-owner)

 

&nbsp;&nbsp;[1.2. Cloud Roles](#12-cloud-roles)

 

&nbsp;&nbsp;&nbsp;&nbsp;[1.2.1. RACI](#121-raci)

 

&nbsp;&nbsp;&nbsp;&nbsp;[1.2.2. RBAC](#122-rbac)

 

&nbsp;&nbsp;[1.3. Architecture Design](#13-architecture-design)

 

&nbsp;&nbsp;[1.4. Configuration Options](#14-configuration-options)

 

&nbsp;&nbsp;[1.5. Networking](#15-networking)

 

&nbsp;&nbsp;&nbsp;&nbsp;[1.5.1 DNS Record](#151-DNS-record)

 

&nbsp;&nbsp;[1.6. Backup](#16-backup)

 

&nbsp;&nbsp;[1.7. Scalability](#17-scalability)

 

&nbsp;&nbsp;&nbsp;&nbsp;[1.7.1 Throughput Units](#171-throughput-units)

 

&nbsp;&nbsp;&nbsp;&nbsp;[1.7.2 Processing Units](#172-processing-units)

 

&nbsp;&nbsp;&nbsp;&nbsp;[1.7.3 Partitioning](#173-partitioning)

 

&nbsp;&nbsp;&nbsp;&nbsp;[1.7.4 Namespace Auto-Inflate](#174-namespace-auto-inflate)

 

&nbsp;&nbsp;[1.8. Resource Updates](#18-resource-updates)

 

&nbsp;&nbsp;[1.9. Automation](#19-automation)

 

&nbsp;&nbsp;[1.10. Modeling](#110-modeling)

 

&nbsp;&nbsp;[1.11. Changes](#111-changes)

 

&nbsp;&nbsp;[1.12. Monitoring](#112-monitoring)

 

&nbsp;&nbsp;&nbsp;&nbsp;[1.12.1 Metrics](#1121-metrics)

 

&nbsp;&nbsp;&nbsp;&nbsp;[1.12.2 Alerts](#1122-alerts)

 

&nbsp;&nbsp;[1.13. High Availability](#113-high-availability)

 

&nbsp;&nbsp;[1.14. Disaster recovery](#114-disaster-recovery)

 

&nbsp;&nbsp;[1.15. Policies](#115-policies)

 

&nbsp;&nbsp;[1.16. Nomenclature](#116-nomenclature)

 

&nbsp;&nbsp;[1.17. Licensing](#117-licensing)

 

&nbsp;&nbsp;[1.18. Security](#118-security)

 

[2. **Deployment**](#2-deployment)

 

[3. **Operation**](#3-operation)

 

&nbsp;&nbsp;[3.1 Upgrades](#31-upgrades)

 

&nbsp;&nbsp;[3.2 Backup](#32-backup)

 

&nbsp;&nbsp;[3.3 Remediation](#33-remediation)

 

&nbsp;&nbsp;[3.4 Changes](#34-changes)

 

[4. **Costs**](#4-costs)

 

&nbsp;&nbsp;[4.1. Capacity](#41-capacity)

 

&nbsp;&nbsp;[4.2. Optimization](#42-optimization)

 

[5. **Decommission**](#5-decommission)

 

[6. **References**](#6-references)#

 

# 1. **ARCHITECTURE**

 

## 1.1. **Owner**

The owner of this kind of resource within Iberdrola’s organization is the "GLOBAL CLOUD TEAM" operational group.

## 1.2. **Cloud Roles**

### 1.2.1. **RACI**

Iberdrola has identified the following RACI Matrix for Azure resources: [Cloud RACI – Teams link.](https://teams.microsoft.com/l/file/6576624C-1D0E-4A06-B1AA-1D8F3911A868?tenantId=031a09bc-a2bf-44df-888e-4e09355b7a24&amp;fileType=xlsx&amp;objectUrl=https%3A%2F%2Fiberdrola.sharepoint.com%2Fsites%2FARQ-CLOUD%2FShared%20Documents%2FCloud%20Team%20(internal)%2FGovernance%20Model%2FGOVERNANCE_RACI%20Matrix_20210324_Validated.xlsx&amp;baseUrl=https%3A%2F%2Fiberdrola.sharepoint.com%2Fsites%2FARQ-CLOUD&amp;serviceName=teams&amp;threadId=19:61aada69b6be45a0bb1397a0416a9f30@thread.skype&amp;groupId=b96b95b9-60a1-40ec-9a86-1041b6598ac1)

### 1.2.2. **RBAC**

Based on Azure’s definition that can be found here:

[Event Hubs Roles](https://docs.microsoft.com/en-us/azure/event-hubs/authorize-access-azure-active-directory)

 

The following [built in roles](https://docs.microsoft.com/en-us/azure/event-hubs/authorize-access-azure-active-directory#azure-built-in-roles-for-azure-event-hubs) are available for authorizing access to Event Hubs:

 

|  |Azure Event Hubs Data Owner|Azure Event Hubs Data Sender|Azure Event Hubs Data Receiver|

| - | :-: | :-: | :-: |

|Developer| X | X | X |

|Event Producer|  | X |  |

|Event Receiver|  |  | X |

 

## 1.3. **Architecture Design**

 

The following image outlines the Architecture for Event Hubs

 

 

![](https://github.corp.iberdrola.com/Iberdrola/azure_documentation/blob/master/Images/AzureEventHubs/01-eh-architecture.png)

 

**Event Producer** – any data source sending data to an event hub. Events can be published using AMQP, HTTPS or Kafka

 

**Partition** – an ordered sequence of events that is held in an event hub and determines the downstream parallelism. Each consumer reads a specific subset (partition) of the event stream

 

**Consumer Group** – a view of the entire Event Hub which enables consuming applications to each have a separate view of the event stream. They read the stream independently, at their own pace with their own offsets

 

**Event Receiver** – any entity which reads data from the Event Hub. Connections are via an AMQP session. Kafka consumers connect using the Kafka protocol

 

**Event Hub Namespace** – a scoping container for Event Hub/Kafka topics which has a unique FQDN. The namespace serves as a container for multiple Event Hub/Kafka topics

 

## 1.4. **Configuration Options**

 

### 1.4.1 **EventHub Namespace Configuration

 

Before creating an Event Hub instance is mandatory to create an Event Hub Namespace. The following table shows the most important parameters and how they will be configured in all the instances created in the Iberdrola subscriptions:

 

|**Parameter**|**Value**|

| - | - |

|Namespace name|View Nomenclature|

|Minimum TLS Version|TLS 1.2|

|Connectivity method|Private Access|

 

 

The following tables show the parameters that are different depending on the environment:

 

|**Parameter**|**PRODUCTION**|**PREPRODUCTION**|**OTHERS**|

| :-: | :-: | :-: | :-: |

| Pricing Tier  | Premium or Standard | Standard | Basic |

| Throughput Units  | 2 | 2 | 1 |

| Enable Auto-Inflate  | True | True | False |

| Auto-Inflate Maximum Throughput Units | 4 | 3 | N/A |

| Processing Units | 1 | N/A | N/A |

 

> :page_with_curl: In Production environments there will be two options when it comes to Pricing Tier, the choice of each will depend on the performance required.

 

There are other configuration options that we can configure after installation, or in case the installation is done via Terraform, at the same time that the installation. The values are the following:

 

| **Parameter Name**  | **Value** |

| :-- | :--- |

| Encryption | Microsoft-managed key |

| Geo-Disaster Recovery | Disabled |

 

 

### 1.4.2 **EventHub  Configuration**

 

The following table shows the most important parameters and how they will be configured in all the instances created in the Iberdrola subscriptions:

 

|Parameter|Value|

| - | - |

|Availability Zones|1,2,3|

|Resource Group Name|<>|

|Resource Group Location|\<West Europe\>|

|Namespace name|\<Namespace-Name\>Application|

|Namespace Location|Western Europe, Northern Europe|

|Event Hub Name|evh-\<application name\>-\[resource name\]-\<environment\>|

|MessageRetentionInDays|1|

|partitionCount|2|

|captureEnabled|False|

 

 

If persistence of Event messages is required, captureEnable should be set to True and the following parameters apply

 

|Parameter|Value|

| - | - |

|captureEncodingFormat|Avro|

|captureTime|300|

|captureSize|314572800|

|captureNameFormat||

|destinationStorageAccountResourceId||

|blobContainerName||

|subscriptionId||

|dataLakeAccountName||

|dataLakeFolderPath||

|Container monitoring|Enabled|

|Azure Policy|Enabled|

 

 

**Note** may ultimately use Terraform code located in the GitHub repository. In thhat case, the name and location of the repositories will be reflected here once known

 

## 1.5. **Networking**

 

The following outbound ports need to be open to use these protocols to communicate with Azure Event Hubs:

|**Protocol**|**Ports**|

| - | - |

|AMQP|5671, 5672|

|HTTPS|443|

|Kafka|9093|

 

IP addresses to allow through the corporate firewall

|Destination Endpoint|IP Addresses|

| - | - |

|\<namespace\>.servicebus.windows.net|1. nslookup \<namespace\>.servicebus.windows.net <br/>2. If using Zone redundancy, note IP addresses for <br/>\<name\>-s1.cloudapp.net <br/>\<name\>-s2.cloudapp.net <br/>\<name\>-s3.cloudapp.net <br/>3. Run nslookup for each|

 

### 1.5.1. **DNS Record**

 

To integrate an Event Hubs namespace with Azure Private Link, it is necessary to create a private DNS zone for Event Hubs domain and create an association link with the virtual network

“privatelink.servicebus.windows.net”

 

[https://docs.microsoft.com/en-us/azure/event-hubs/private-link-service#configure-the-private-dns-zone](https://docs.microsoft.com/en-us/azure/event-hubs/private-link-service#configure-the-private-dns-zone)

 

## 1.6. **Backup**

 

Event Hubs implements transparent failure detection and failover mechanisms such that the service will continue to operate within assured service levels without noticeable interruptions occur resulting from failures of machines or complete racks across clusters spanning multiple failure domains within a datacentre. With **availability zones** enabled at the Event Hubs namespace level, this risk is further spread across three physically separated facilities to instantly cope with complete loss of an entire facility.

Depending on the subscription, Event Hubs Geo-disaster recovery may also be an option to cope with an outage of an entire availability zone

 

## 1.7. **Scalability**

 

Event Hubs have two factors which affect scaling:

* Purchased Units : Throughput units (Standard Tier) and Processing units (Premium Tier)

* Partitioning

 

### 1.7.1. **Throughput Units**

 

For Standard Tier Event Hub deployments, throughput units set the throughput capacity of and are pre-purchased capacity. A single throughput provides:

* Ingress up to 1MB per second or 1000 events per second

* Egress up to 2MB per second or 4096 events per second

 

Exceeding Ingress limits will return a ServerBusyException although exceeding Egress limits will not produce an exception. It’s important to check purchased throughput units if experiencing publishing exceptions or expecting increased egress demand.

Throughput units:

 

1. are allocated at the namespace level

2. can be managed on the namespace Scale blade in the Azure portal

3. can be managed programmatically using Event Hub APIs

 

_See section [1.7.4 Namespace Auto-Inflate](#174-namespace-auto-inflate) for details on Auto Inflate capability of namespace throughput units_

 

### 1.7.2. **Processing Units**

 

Premium Tier deployments offer superior performance within a managed multitenant PaaS environment. Resources are isolated at a CPU and memory level so tenant each workload runs in isolation. The resource containers are called Processing Units and you can purchase 1,2,4,8,16 Pus per Event Hubs Premium namespace

1 Processing Unit offer ~5-10MB/s ingress and 10-20MB/s egress capacity

 

### 1.7.3. **Partitioning**

 

Sequences of events are organised sequentially into one or more partitions

 

![](https://github.corp.iberdrola.com/Iberdrola/azure_documentation/blob/master/Images/AzureEventHubs/multiple-partitions.png)

 

Analogous to a commit log, partitions hold event data, user defined properties describing the event and processing metadata such as message offset and service timestamp.

 

Reasons to use partitions:

 

* Maintaining a log per partition requires that events are kept together in underlying storage and replicas. Also allows for multiple parallel logs for the same event hub, multiplying raw IO throughput capacity

* Apps must be able to keep up with processing the volume of events that are being sent into an event hub. This may be complex and requires substantial, scaled-out, parallel processing capacity. The capacity of a single process to handle events is limited, so you need several processes. Partitions are how your solution feeds those processes while ensuring that each event has a clear processing owner.

 

### 1.7.4. **Namespace Auto-Inflate**

 

Auto-Inflate automatically scales the number of Throughput Units assigned to Standard Tier Event Hubs Namespace when traffic exceeds the capacity of the Throughput Units assigned to it. It’s possible to specify a limit to which the Namespace will automatically scale.

The Namespace Auto-Inflate will be configured from the Terraform IaC with the following parameters:

 

|Terraform Parameter|Value|Description|

| - | - | - |

|isAutoInflateEnabled|True|Enable Namespace auto inflate|

|Maximum_throughput_units|<1-20>|(Optional) Specifies the maximum number of throughput units when Auto Inflate is Enabled. Valid values range from 1-20|

|namespace_name|evhns\-\<application name\>\-\[resource name\]\-\<environment\>|(Required) Specifies the name of the EventHub Namespace. Changing this forces a new resource to be created.|

|resource_group_name|rg\-\<application name\>\-\[resource name\]-\<environment\>|(Required) The name of the resource group in which the EventHub's parent Namespace exists. Changing this forces a new resource to be created.|

|partition_count|2|(Required) Specifies the current number of shards on the Event Hub. Changing this forces a new resource to be created|

|message_retention|1|(Required) Specifies the number of days to retain the events for this Event Hub.|

 

Additionally, to configure the Namespace auto-inflate and partitions, although it is possible to configure using Terraform, yaml files will be used which will be part of the Helm deployment charts.

 

## 1.8. **Resource Updates**

 

Event Hubs are a fully managed PaaS service with no mechanism available for applying updates at an Azure resource level

 

## 1.9. **Automation**

 

Not considered yet (at this stage)

 

## 1.10. **Modeling**

 

For recording Cis for Event Hubs, there are two elements that have to be modelled in the CMDB:

 

* Event Hub Namespaces

* Event Hubs

 

To manage within the scope of the Azure Cloud, the following attributes are also required:

 

|Attribute|Description|

| - | - |

|Subscription|The name of the subscription where it is located|

|Region|The name of the region where it is located|

|Resource Group|The name of the Resource Group where it is located|

|||

|||

 

## 1.11. **Changes**

 

There will be several initial configurations that will be susceptible to being changed throughout the life cycle of the resource. These are some of them:

 

* Enable Event Hub AutoInflate

* Add or Remove Event Hub to/from Namespace

* Add or Remove Consumer Group to/from Event Hub

 

## 1.12. **Monitoring**

 

Where critical applications and business processes are relying on Azure resources, these resources should be monitored for availability, performance and operation

 

The following types of monitoring data are available

 

* **Platform metrics** - Platform metrics are collected automatically into Azure Monitor Metrics with no configuration required. Create a diagnostic setting to send entries to Azure Monitor Logs or to forward them outside of Azure.

* **Resource logs** - Resource logs are automatically generated by Azure resources but not collected without a diagnostic setting. Create a diagnostic setting to send entries to Azure Monitor Logs or to forward them outside of Azure.

* **Activity log** - The Activity log is collected automatically with no configuration required and can be viewed in the Azure portal. Create a diagnostic setting to copy them to Azure Monitor Logs or to forward them outside of Azure.

 

### 1.12.1 **Metrics**

### 1.12.2. **Alerts**

## 1.13. **High Availability**

 

High availability can be specified at the Event Hub Namespace level:

|Parameter|Value|

| - | - |

|Zone Redundancy|Enabled|

 

## 1.14. **Disaster recovery**

 

No option (2nd region) as yet. When available, Azure provides metadata disaster recovery for Event Hubs and relies on primary and secondary disaster recovery namespaces. Connection string changes are not required as connection is via an alias. These are the main concepts for disaster recovery:

**Alias**: The name for a disaster recovery configuration that you set up. The alias provides a single stable Fully Qualified Domain Name (FQDN) connection string. Applications use this alias connection string to connect to a namespace.

**Primary/secondary namespace**: The namespaces that correspond to the alias. The primary namespace is "active" and receives messages (can be an existing or new namespace). The secondary namespace is "passive" and doesn't receive messages. The metadata between both is in sync, so both can seamlessly accept messages without any application code or connection string changes. To ensure that only the active namespace receives messages, you must use the alias.

**Metadata**: Entities such as event hubs and consumer groups; and their properties of the service that are associated with the namespace. Only entities and their settings are replicated automatically. Messages and events aren't replicated.

**Failover **: The process of activating the secondary namespace.

 

At a high level, setup of failover can be represented by:

 

![](https://github.corp.iberdrola.com/Iberdrola/azure_documentation/blob/master/Images/AzureEventHubs/Failover.PNG)

 

## 1.15. **Policies**

 

|Name|Description|Effects|

| - | - | - |

|All authorization rules except RootManageSharedAccessKey should be removed from Event Hub namespace|Event Hub clients should not use a namespace level access policy that provides access to all queues andtopics in a namespace. To align with the least privilege security model, you should create access policies at the entity level for queues and topics to provide access to only the specific entity|Audit, Deny, Disabled|

|Authorization rules on the Event Hub instance should be defined|Audit existence of authorization rules on Event Hub entities to grant leastprivileged access|AuditIfNotExists, Disabled|

|Configure Event Hub namespaces with private endpoints|Private endpoints connect your virtual network to Azure services without a public IP address at the source or destination. By mapping private endpoints to Event Hub namespaces, you can reduce data leakage risks. Learn more at:[https://docs.microsoft.com/azure/event-hubs/privatelink-service](https://docs.microsoft.com/azure/event-hubs/privatelink-service).|DeployIfNotExists, Disabled|

|Deploy Diagnostic Settings for Event Hub to Event Hub|Deploys the diagnostic settings for Event Hub to stream to a regional Event Hub when any Event Hub which is missing this diagnostic settings is created or updated|DeployIfNotExists, Disabled|

|Deploy Diagnostic Settings for Event Hub to Log Analytics workspace|Deploys the diagnostic settings for Event Hub to stream to a regional Log Analytics workspace when any Event Hub which is missing this diagnostic settings is created or updated.|DeployIfNotExists, Disabled|

|Event Hub namespaces should use a customermanaged key for encryption|Azure Event Hubs supports the option of encrypting data at rest with either Microsoft-managed keys (default) or customermanaged keys. Choosing to encrypt data using customer-managed keys enables you to assign, rotate, disable,  and revoke access to the keys that Event Hub will use to encrypt data in your namespace. Note that Event Hub only supports encryption with  customermanaged keys for namespaces in dedicated clusters.|Audit, Disabled|

|Event Hub namespaces should use private link|Azure Private Link lets you connect your virtual network to Azure services without a public IP address at the source or destination. The Private Link platform handles the connectivity between the consumer and services over the Azure backbone network. By mapping private endpoints to Event Hub namespaces, data leakage risks are reduced. Learn more at:https://docs.microsoft.com/azure/event-hubs/privatelink-service|AuditIfNotExists, Disabled|

|Event Hub should use a virtual network service endpoint|This policy audits any Event Hub not configured to use a virtual network service endpoint.|AuditIfNotExists, Disabled|

|Resource logs in Event Hub should be enabled|Audit enabling of resource logs. This enables you to recreate activity trails to use for investigation purposes; when a security incident occurs or when your network is compromised|AuditIfNotExists, Disabled|

||||

 

## 1.16. **Nomenclature**

 

Iberdrola has identified the following naming convention for Azure Event Hubs:

evh\-\<application name\>\-\[resource name\]\-\<environment\>

For example, evh-navigator-prod

Reference : AzureIberdrolaNamingandTaggingv1.xlsx

 

## 1.17. **Licensing**

 

No licenses are involved in this type of resource.

 

## 1.18. **Security**

# 2. **DEPLOYMENT**

 

At this stage, deployment of Event Hubs will be manual however automation using Terraform will be considered for future iterations.

 

# 3. **OPERATION**

## 3.1. **Upgrades**

## 3.2. **Backup**

 

If backup of event data is a requirement, Event Hubs Capture (see section [1.4. Configuration Options](#14-configuration-options)) should be used. By default (capture is disabled), the event data will be stored in eventhub in 7 days (maximum retention period). In that case, if events are not processed in 7 days, then the event data will be lost.

By enabling and configuring the Capture feature, it will be possible to reuse the event data which will be stored in blob storage.

 

## 3.3. **Remediation**

## 3.4. **Changes**

# 4. COSTS

## 4.1. Capacity

 

Azure Event Hubs is available in multiple [service tiers](https://docs.microsoft.com/en-gb/azure/event-hubs/compare-tiers): Basic, Standard, Premium and Dedicated. For the architecture design that has been established, the Standard SKU is planned.

 

Standard Event Hubs provide support for 20 Consumer groups per Event Hub and 5000 Brokered connections per namespace. The subscription metric is Throughput Units

 

 

## 4.2. Optimization

# 5. **DECOMMISSION**

 

After verifying with the relevant resource owners, decommissioning can be performed at the Event Hub or Event Hub Namespace level:

* Event Hub Namespaces can be deleted using the Azure Portal or using the [az eventhubs namespace delete](https://docs.microsoft.com/en-us/cli/azure/eventhubs/namespace?view=azure-cli-latest#az-eventhubs-namespace-delete) command

* Event Hubs can be deleted using the Azure Portal or using the [az eventhubs eventhub delete](https://docs.microsoft.com/en-us/cli/azure/eventhubs/eventhub?view=azure-cli-latest#az-eventhubs-eventhub-delete) command

 

# 6. **REFERENCES**