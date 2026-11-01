<div align="center">

# ⛏️ SlimefunCore 4.0

### Fork não oficial do Slimefun 4 — Paper 1.21.11

![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11-62b47a?style=for-the-badge&logo=minecraft&logoColor=white)
![Paper API](https://img.shields.io/badge/Paper%20API-1.21.11-blue?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Licença](https://img.shields.io/badge/Licen%C3%A7a-GPL--3.0-orange?style=for-the-badge&logo=gnu&logoColor=white)

<br>

<img src="https://raw.githubusercontent.com/Slimefun/Slimefun-Wiki/master/images/showcase1.png" width="620" alt="Demonstração do Slimefun">

<br><br>

**[Código-fonte](https://github.com/davitremea1-ops/Slimefun4.5Core)** ·
**[Pull Request de compatibilidade](https://github.com/davitremea1-ops/Slimefun4.5Core/pull/1)** ·
**[Relatar um problema](https://github.com/davitremea1-ops/Slimefun4.5Core/issues)** ·
**[Wiki original](https://github.com/Slimefun/Slimefun4/wiki)** ·
**[Discord](https://discord.gg/hJwXe3tw4)**
**[Repositório](https://github.com/davitremea1-ops/Slimefun4.5Core)** ·
**[Pull Request de compatibilidade](https://github.com/davitremea1-ops/Slimefun4.5Core/pull/1)** ·
**[Relatar um problema](https://github.com/davitremea1-ops/Slimefun4.5Core/issues)** ·
**[Wiki original](https://github.com/Slimefun/Slimefun4/wiki)** ·
**[Projeto original](https://github.com/Slimefun/Slimefun4)**

</div>

---

## Sobre o projeto

O **Slimefun** amplia a experiência de um servidor Minecraft sem exigir que os jogadores instalem mods no cliente. Ele adiciona centenas de itens, máquinas, receitas, sistemas de energia, automação, ferramentas e recursos de exploração.

Este repositório contém uma versão modificada e não oficial do SlimefunCore. O projeto usa a API do Paper **1.21.11**, é compilado para **Java 21** e inclui um gerenciador próprio para add-ons configurados.

---

## Sobre o projeto

O **Slimefun** transforma um servidor Minecraft em uma experiência semelhante a um modpack, sem exigir que os jogadores instalem mods no cliente. O projeto adiciona máquinas, sistemas de energia, ferramentas, magia, automação e muitos outros itens.

Este repositório é um fork não oficial baseado no SlimefunCore. O objetivo desta versão é manter o servidor atualizado em **Minecraft 1.21.11** e recuperar a compatibilidade da API do Slimefun com add-ons desenvolvidos para versões anteriores.

> Este fork não é oficial e não é afiliado aos mantenedores do Slimefun original.

## Versões e requisitos

| Componente | Alvo |
|:---|:---|
| Minecraft | **1.21.11** |
| Software de servidor | **Paper ou Purpur** |
| Java | **21** |
| API usada na compilação | **Paper API 1.21.11** |
| Idioma padrão | **Português do Brasil (pt-BR)** |
| Licença | **GNU GPL v3.0** |

**Importante:** este projeto não tenta fazer o servidor 1.21.11 executar plugins feitos para qualquer versão do Minecraft. O foco é manter add-ons do ecossistema Slimefun que esperam encontrar a API histórica do Slimefun, enquanto o servidor continua em 1.21.11.

## Compatibilidade com add-ons

Uma das causas de incompatibilidade entre versões do Slimefun é a alteração de classes, métodos, construtores ou comportamentos que add-ons antigos utilizam. Por isso, o trabalho deste fork prioriza a compatibilidade de código-fonte e binária da API pública.

### Contratos legados preservados ou restaurados

- **`SlimefunItemStack` herda de `org.bukkit.inventory.ItemStack`:** add-ons podem continuar passando um `SlimefunItemStack` diretamente para métodos Bukkit/Paper que recebem `ItemStack`.
- **Construtores históricos:** os 13 construtores públicos da versão de referência foram mantidos.
- **Bloqueio de modelos:** `SlimefunItemStack.lock()` continua disponível.
- **Imutabilidade:** o gancho protegido `SlimefunItem.isItemStackImmutable()` continua disponível para subclasses.
- **Exceção legada:** `WrongItemStackException` continua presente.
- **`clone()`:** o retorno covariante mantém a ponte binária esperada por código compilado contra a API anterior.
- **Cópia editável:** `SlimefunItemStack.item()` devolve uma cópia Bukkit comum, separada do modelo original.

Os pacotes históricos da API, incluindo `io.github.thebusybiscuit.slimefun4.api` e `me.mrCookieSlime.Slimefun.api`, também são tratados como parte do contrato de compatibilidade.

### O que ainda precisa ser verificado

Preservar a API do Slimefun não garante automaticamente que todos os add-ons funcionem. Cada complemento pode depender de bibliotecas próprias, NMS, métodos internos não documentados, eventos ou comportamentos específicos do servidor.

Por isso, add-ons importantes devem ser testados individualmente em um servidor Paper/Purpur 1.21.11. Quando um add-on apresentar erro, o ideal é analisar o stack trace e corrigir a incompatibilidade concreta, em vez de ocultar o erro ou ignorar a verificação de dependências.

## Recursos do Slimefun

| Área | Exemplos |
|:---|:---|
| ⚙️ Tecnologia | Máquinas, geradores, reatores e redes de energia |
| 🏭 Automação | Fabricação automática, transporte e processamento de itens |
| 🔮 Magia | Altares, runas, talismãs e itens mágicos |
| 🎒 Equipamentos | Ferramentas, armaduras, mochilas e utilidades |
| 🧭 Exploração | GPS, recursos geológicos e sistemas especiais |

## Gerenciador de add-ons

O fork inclui um gerenciador para listar e administrar add-ons configurados. Ele pode trabalhar com arquivos instalados manualmente e com os add-ons definidos na configuração local/remota.

### Comandos

Os comandos estão disponíveis pelo comando dedicado `/addon` e pelo subcomando `/sf addon`.

| Comando | Função |
|:---|:---|
| `/addon list` | Lista os add-ons instalados e gerenciados |
| `/addon status` | Mostra o estado da configuração e da sincronização |
| `/addon sync` | Solicita sincronização manual |
| `/addon download <nome>` | Baixa um add-on configurado |
| `/addon update <nome>` | Atualiza um add-on configurado |
| `/addon enable <nome>` | Habilita um add-on gerenciado |
| `/addon disable <nome>` | Desabilita um add-on gerenciado |
| `/addon remove <nome>` | Agenda a remoção do add-on gerenciado |

Os mesmos subcomandos podem ser usados com `/sf addon`, por exemplo `/sf addon list`.

> O gerenciador não converte nem reescreve add-ons incompatíveis. Ele administra os arquivos configurados; a compatibilidade do código do add-on continua dependendo da API que ele utiliza.

## Idioma: Português do Brasil

O idioma padrão do fork é **pt-BR**. Os arquivos de tradução foram alinhados às chaves do idioma inglês nas seguintes áreas:

- Mensagens e interface;
- Categorias;
- Receitas;
- Pesquisas;
- Recursos.

A configuração padrão também seleciona `pt-BR`. Outros idiomas podem continuar sendo selecionados pela configuração do servidor, desde que estejam disponíveis no projeto.

## Compilar o projeto

### Requisitos

- Git;
- Java Development Kit **21**;
- Maven instalado e disponível no terminal.

Confira as versões:

```bash
java -version
mvn -version
```

### Baixar e compilar

```bash
git clone https://github.com/davitremea1-ops/Slimefun4.5Core.git
cd Slimefun4.5Core
mvn clean package
```

O Maven executa a compilação e os testes configurados no projeto. O JAR principal é gerado em `target/` com o nome no formato:

```text
target/SlimefunCore4-<NUMERO_DO_BUILD>.jar
```

O projeto também gera um JAR de fontes com o sufixo `-sources.jar`.

## Instalação no servidor

1. Pare o servidor Paper/Purpur 1.21.11.
2. Faça backup da pasta do servidor e, principalmente, dos dados do Slimefun.
3. Coloque o JAR compilado na pasta `plugins/`.
4. Instale os add-ons e suas dependências conforme a documentação de cada projeto.
5. Inicie o servidor e confira o console para identificar erros de carregamento.
6. Teste os itens, máquinas, receitas e recursos dos add-ons antes de liberar o servidor aos jogadores.

Não substitua o JAR de uma instalação em produção sem backup. Evite também instalar duas cópias do mesmo add-on, pois isso pode causar conflito de carregamento.

## Estado do desenvolvimento

| Área | Estado |
|:---|:---|
| Alvo Paper/Purpur 1.21.11 e Java 21 | Configurado |
| Restauração de contratos da API legada | Implementada na branch de trabalho |
| Testes de contrato da API | Adicionados; execução pendente |
| Tradução pt-BR | Chaves alinhadas ao idioma inglês |
| Build Maven / GitHub Actions | Ainda não confirmado |
| Testes com add-ons de terceiros | Pendentes |

O trabalho está sendo acompanhado no [Pull Request #1](https://github.com/davitremea1-ops/Slimefun4.5Core/pull/1). Ele permanece como rascunho e ainda não foi incorporado à branch principal.

**Não considere esta versão pronta para produção até que a compilação passe e os add-ons utilizados no seu servidor sejam testados em Paper/Purpur 1.21.11.**

## Capturas de tela

As imagens abaixo são do projeto e da comunidade Slimefun original.

<div align="center">

<img src="https://raw.githubusercontent.com/Slimefun/Slimefun-Wiki/master/images/showcase1.png" width="270" alt="Demonstração do Slimefun">
<img src="https://raw.githubusercontent.com/Slimefun/Slimefun-Wiki/master/images/showcase6.png" width="270" alt="Máquinas do Slimefun">
<img src="https://raw.githubusercontent.com/Slimefun/Slimefun-Wiki/master/images/showcase5.png" width="270" alt="Itens do Slimefun">

</div>

## Créditos e licença

Este projeto utiliza como base o trabalho dos desenvolvedores e colaboradores do Slimefun original.

- **Projeto original:** [Slimefun](https://github.com/Slimefun/Slimefun4)
- **Criador do projeto original:** [TheBusyBiscuit](https://github.com/TheBusyBiscuit)
- **Base deste fork:** [SlimeCore-MC/Slimefun4Core](https://github.com/SlimeCore-MC/Slimefun4Core)
- **Código deste fork:** [davitremea1-ops/Slimefun4.5Core](https://github.com/davitremea1-ops/Slimefun4.5Core)

O projeto está sob a **GNU General Public License v3.0 (GPLv3)**. Redistribuições e modificações devem respeitar os termos da licença e preservar os avisos de copyright aplicáveis. Consulte o arquivo [LICENSE](LICENSE).

<div align="center">

### 💚 Obrigado por conferir o SlimefunCore!

*Uma experiência no estilo modpack, dentro do Minecraft vanilla.*

</div>
