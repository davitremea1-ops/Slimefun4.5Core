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

> Este fork não é uma distribuição oficial do Slimefun e não é afiliado aos mantenedores do projeto original.

## Versões e requisitos

| Componente | Configuração do projeto |
|:---|:---|
| Minecraft / API de servidor | **1.21.11** |
| API de compilação | **Paper API 1.21.11** |
| Versão mínima declarada no plugin.yml | **1.21** |
| Java | **21** |
| Build | **Maven** |
| Licença | **GNU GPL v3.0** |

A versão da API configurada no `pom.xml` é Paper 1.21.11. O `plugin.yml` declara `api-version: '1.21'`; isso, por si só, não significa que todas as versões menores de Minecraft tenham sido testadas. Use Paper 1.21.11 como alvo de referência. Outros forks compatíveis com Paper, como Purpur, também devem ser validados no servidor em que serão utilizados.

## Recursos

| Área | Exemplos |
|:---|:---|
| ⚙️ Tecnologia | Máquinas, geradores, reatores e sistemas de energia |
| 🏭 Automação | Fabricação, processamento e transporte de itens |
| 🔮 Magia | Altares, runas, talismãs e itens especiais |
| 🎒 Equipamentos | Ferramentas, armaduras, mochilas e utilidades |
| 🧭 Exploração | GPS, recursos geológicos e sistemas de exploração |

## Gerenciador de add-ons

O código inclui um gerenciador para add-ons cadastrados na configuração local/remota. A configuração distribuída em `src/main/resources/addonmanager.yml` habilita a sincronização com uma lista hospedada no GitHub e define URLs específicas por versão.

Os comandos podem ser usados diretamente com `/addon` ou pelo subcomando `/sf addon`.

| Comando | Função |
|:---|:---|
| `/addon list` | Lista os add-ons reconhecidos pelo gerenciador |
| `/addon status` | Mostra o estado dos add-ons e da sincronização |
| `/addon sync` | Solicita uma sincronização da configuração remota |
| `/addon download <nome>` | Baixa um add-on configurado |
| `/addon update <nome>` | Verifica e aplica uma atualização disponível |
| `/addon enable <nome>` | Habilita um add-on gerenciado |
| `/addon disable <nome>` | Desabilita um add-on gerenciado |
| `/addon remove <nome>` | Remove um add-on gerenciado |

As alterações de habilitação e desabilitação podem exigir reinicialização do servidor. O gerenciador administra os arquivos e as entradas configuradas; ele não modifica automaticamente o código de add-ons de terceiros para corrigir incompatibilidades.

### Permissões do gerenciador

- `addon.admin`: administração completa.
- `addon.update`: sincronização, download e atualização.
- `addon.manage`: habilitar, desabilitar e remover add-ons.

## Compatibilidade com add-ons antigos

É importante distinguir duas coisas:

1. **Versão do Minecraft:** este projeto está configurado para compilar contra a Paper API 1.21.11.
2. **API do Slimefun:** add-ons antigos podem depender de classes, construtores, métodos e comportamentos que existiam em versões anteriores do Slimefun.

Na versão que está atualmente na branch `main`, `SlimefunItemStack` é implementado como uma classe que mantém um `ItemStack` interno; ela **não herda diretamente de** `org.bukkit.inventory.ItemStack`. Por isso, um add-on legado que espera passar um `SlimefunItemStack` diretamente para um método Bukkit/Paper que recebe `ItemStack` pode não ser compatível.

O [Pull Request #1](https://github.com/davitremea1-ops/Slimefun4.5Core/pull/1) contém um trabalho separado para restaurar contratos da API legada e adicionar testes de compatibilidade. Essas alterações ainda não fazem parte desta branch principal. Não considere a compatibilidade universal de add-ons antigos garantida até que o PR seja integrado e os complementos utilizados sejam testados individualmente.

Add-ons que dependem de NMS, bibliotecas próprias, eventos internos ou detalhes não documentados podem precisar de correções específicas mesmo depois da restauração da API pública.

## Idiomas

Os arquivos de idioma ficam em `src/main/resources/languages/`. O repositório inclui uma pasta `pt-BR` com arquivos de mensagens, categorias, receitas, pesquisas e recursos.

**Atenção:** a configuração atual de `main` usa inglês (`options.language: en`) como idioma padrão. Para selecionar português do Brasil, altere a opção `language` no arquivo `config.yml` do Slimefun para `pt-BR` e reinicie o servidor. A cobertura e a revisão de cada texto traduzido podem variar.

## Compilar

### Requisitos

- Git;
- Java Development Kit (JDK) **21**;
- Maven.

Confira as versões instaladas:

```bash
java -version
mvn -version
```

Clone o repositório e execute o build:

```bash
git clone https://github.com/davitremea1-ops/Slimefun4.5Core.git
cd Slimefun4.5Core
mvn clean package
```

O Maven executa as etapas de compilação, testes configurados e empacotamento. O artefato principal é criado em `target/` com o nome:

```text
target/SlimefunCore4-<NUMERO_DO_BUILD>.jar
```

Também é gerado um JAR de fontes com o sufixo `-sources.jar`.

**Observação sobre Linux:** o `pom.xml` que está atualmente em `main` chama o executável `powershell` para atualizar o número do build. Em ambientes Linux, esse comando precisa estar instalado e disponível com esse nome. O PR de compatibilidade inclui uma implementação específica para Unix, mas ela ainda não foi incorporada a `main`.

## Instalação

1. Faça backup do servidor e dos dados do Slimefun.
2. Pare o servidor Paper 1.21.11.
3. Coloque o JAR compilado na pasta `plugins/`.
4. Instale os add-ons e as dependências exigidas por cada projeto.
5. Inicie o servidor e confira o console em busca de erros.
6. Teste os itens, receitas, máquinas e integrações antes de liberar o servidor aos jogadores.

Não substitua o JAR de uma instalação em produção sem backup. Evite instalar cópias duplicadas de um mesmo add-on.

## Estado do desenvolvimento

| Área | Situação |
|:---|:---|
| Alvo Paper API 1.21.11 | Configurado no Maven |
| Compilação para Java 21 | Configurada no Maven |
| Gerenciador de add-ons | Implementado no código |
| Arquivos de idioma pt-BR | Presentes no repositório |
| Compatibilidade completa com add-ons legados | Em desenvolvimento no PR #1 |
| Build e testes no GitHub Actions | Ainda sem execução registrada nesta revisão |
| Testes com add-ons de terceiros em servidor real | Necessários |

**Não interprete a configuração do Maven como prova de que o JAR já foi validado em um servidor real.** Faça backup e teste em ambiente separado antes de utilizar em produção.

## Capturas de tela

As imagens abaixo são do projeto e da comunidade Slimefun original.

<div align="center">

<img src="https://raw.githubusercontent.com/Slimefun/Slimefun-Wiki/master/images/showcase1.png" width="270" alt="Demonstração do Slimefun">
<img src="https://raw.githubusercontent.com/Slimefun/Slimefun-Wiki/master/images/showcase6.png" width="270" alt="Máquinas do Slimefun">
<img src="https://raw.githubusercontent.com/Slimefun/Slimefun-Wiki/master/images/showcase5.png" width="270" alt="Itens do Slimefun">

</div>

## Créditos e licença

Este fork utiliza trabalho do projeto Slimefun e de seus colaboradores.

- **Projeto original:** [Slimefun 4](https://github.com/Slimefun/Slimefun4)
- **Criador do projeto original:** [TheBusyBiscuit](https://github.com/TheBusyBiscuit)
- **Base relacionada:** [SlimeCore-MC/Slimefun4Core](https://github.com/SlimeCore-MC/Slimefun4Core)
- **Este repositório:** [davitremea1-ops/Slimefun4.5Core](https://github.com/davitremea1-ops/Slimefun4.5Core)

O código está sob a **GNU General Public License v3.0 (GPLv3)**. Modificações e redistribuições devem respeitar os termos da licença e manter os avisos de copyright aplicáveis. Consulte o arquivo [LICENSE](LICENSE).

<div align="center">

### 💚 Obrigado por conferir o SlimefunCore!

*Uma experiência no estilo modpack dentro do Minecraft vanilla.*

</div>
