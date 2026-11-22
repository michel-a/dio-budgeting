# 💰 Smart Budgeting & AI Voice Assistant API

Aplicação backend desenvolvida em **Java 25** e **Spring Boot** para gestão de orçamento pessoal, integrada com **Spring AI (OpenAI)** para permitir o registro e a consulta de despesas por meio de comandos de voz (áudio) com resposta falada em MP3.

---

## 🚀 O que o projeto faz

O sistema permite gerenciar transações financeiras por categoria tanto via requisições REST tradicionais quanto por meio de um assistente inteligente multimodal:

* **Cadastro de Transações (`POST /transactions`):** Registra despesas e receitas diretamente no banco de dados MySQL.
* **Consulta por Categoria (`GET /transactions/{category}`):** Lista todas as transações filtradas por uma categoria específica.
* **Assistente de Orçamento por Voz (`POST /transactions/ai`):**
  1. **Transcrição (Speech-to-Text):** Recebe um arquivo de áudio em português brasileiro (ex.: *"Gastei 50 reais no mercado"*) e o transcreve utilizando o modelo `whisper-1`.
  2. **Processamento Inteligente & Tool Calling:** O `ChatClient` (configurado com `gpt-4o-mini` e o prompt do sistema em `system.st`) interpreta a intenção do usuário e aciona automaticamente os casos de uso da aplicação (`PersistTransactionUseCase` ou `ListTransactionsByCategoryUseCase`) para salvar ou consultar dados no banco.
  3. **Síntese de Voz (Text-to-Speech):** Converte a resposta gerada pela IA em um arquivo de áudio (`audio.mp3`) utilizando o modelo `gpt-4o-mini-tts` (voz `nova`) e o retorna para download/reprodução.

---

## ⚙️ Como executar a aplicação

### Pré-requisitos
* **Java JDK 25** instalado.
* **Docker e Docker Compose** instalados (para subir o banco de dados MySQL automaticamente via `compose.yml`).
* Uma **chave de API da OpenAI** (`OPENAI_API_KEY`).

### Passos para rodar localmente:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/michel-a/dio-budgeting.git
   cd dio-budgeting
   ```

2. **Configure a variável de ambiente com a sua chave da OpenAI:**
   * **No Linux / macOS:**
     ```bash
     export OPENAI_API_KEY="sua-chave-api-aqui"
     ```
   * **No Windows (PowerShell):**
     ```powershell
     $env:OPENAI_API_KEY="sua-chave-api-aqui"
     ```

3. **Execute a aplicação com o Gradle Wrapper** (o Spring Boot Docker Compose subirá os containers necessários automaticamente):
   * **No Linux / macOS:**
     ```bash
     ./gradlew bootRun
     ```
   * **No Windows:**
     ```cmd
     gradlew.bat bootRun
     ```

---

## 🛠️ Tecnologias Usadas

* **Java 25**
* **Spring Boot 4.0.5** (Web, Data JPA, Docker Compose)
* **Spring AI 2.0.0-M4** (Integração com OpenAI: `ChatClient`, `TranscriptionModel` e `TextToSpeechModel`)
* **MySQL** (Banco de dados relacional com `mysql-connector-j`)
* **Lombok**
* **Gradle** (Gerenciamento de dependências e build)
* **Docker / Docker Compose**

---

## 🧪 Como testar o fluxo principal

Você pode testar a aplicação utilizando **Postman**, **Insomnia** ou `curl`:

### 1. Testando o fluxo de IA por Voz (`POST /transactions/ai`)
Envie uma requisição `POST` com o corpo no formato `multipart/form-data` contendo o parâmetro `file` com um arquivo de áudio (`.m4a`, `.mp3`, `.wav`, etc.):

```bash
curl -X POST http://localhost:8080/transactions/ai \
  -F "file=@src/test/resources/audio/recording-1.m4a" \
  --output resposta.mp3
```
A API processará o áudio, executará a ação no banco de dados via *Tool Calling* e salvará a resposta falada no arquivo `resposta.mp3`.

### 2. Testando os Endpoints REST Tradicionais
* **Criar uma transação (`POST /transactions`):** Envie um JSON no corpo da requisição com os dados de `TransactionRequest`.
* **Listar por categoria (`GET /transactions/{category}`):** Acesse a rota informando a categoria desejada na URL.

### 3. Executando os Testes Automatizados
Para rodar a suíte de testes unitários e de integração:
```bash
./gradlew test
```

---

## 💡 O que aprendi durante o desafio

* **Orquestração Multimodal com Spring AI:** Como encadear modelos de transcrição (`TranscriptionModel`), modelos de chat (`ChatClient`) e síntese de voz (`TextToSpeechModel`) em um único fluxo de requisição HTTP.
* **Tool Calling na prática:** Como expor casos de uso da aplicação (`PersistTransactionUseCase` e `ListTransactionsByCategoryUseCase`) como ferramentas para que o modelo de linguagem decida autonomamente quando salvar ou buscar informações no banco de dados.
* **Arquitetura Limpa em Spring Boot:** Separação clara entre camadas de domínio (`domain`), casos de uso (`application`) e adaptadores de entrada/persistência (`infrastructure`).
