# Challenge

![Java](https://img.shields.io/badge/Java-21-blue)
![AWS](https://img.shields.io/badge/AWS-CloudFormation-brightgreen)
![Spring](https://img.shields.io/badge/Spring-Framework-green)

## Descrição

Este projeto é um desafio técnico que envolve:

1. Emissão de faturas (Issues) de 8 a 12 a cada 3 horas para pessoas aleatórias durante 24 horas. O ambiente de emulação Sandbox garantirá que algumas dessas faturas sejam pagas automaticamente.
2. Recebimento do webhook de callback da fatura e envio do valor recebido (menos eventuais taxas) para a seguinte conta usando uma Transferência:
   - **Código do banco:** 20018183
   - **Agência:** 0001
   - **Conta:** 6341320293482496
   - **Nome:** Stark Bank S.A.
   - **CNPJ:** 20.018.183/0001-80
   - **Tipo de conta:** pagamento

## Arquitetura

Aqui está um diagrama da arquitetura do sistema:

![Diagrama de Arquitetura](URL_DO_DIAGRAMA)

O diagrama mostra a interação entre os componentes principais:

- **Usuário/Cliente**: Emite requisições para a aplicação.
- **Aplicação Java/Spring**: Processa requisições, emite faturas e recebe callbacks via API Webhook.
- **API Webhook**: Recebe notificações de pagamentos de faturas.
- **AWS**:
  - **Secret Manager**: Armazena a chave privada.
  - **CloudFormation**: Configura o ambiente (CloudWatch Events e SQS).
  - **SQS**: Fila de mensagens para eventos.
  - **CloudWatch Events**: Agendamento de tarefas para emissão de faturas.
  
## API

A API disponível para recebimento de webhooks está acessível em:

- **URL:** [http://challenge-jonasrbneto.us-east-1.elasticbeanstalk.com/v2/webhook](http://challenge-jonasrbneto.us-east-1.elasticbeanstalk.com/v2/webhook)

