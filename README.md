<div align="center">

# ⛏️ SlimefunCore 4.5 ⛏️
### <sub>✨ VERSÃO MODIFICADA NÃO OFICIAL ✨</sub>
### <sub>Fork mantido por **R2Ksanu**</sub>

<br>

![Minecraft](https://img.shields.io/badge/MINECRAFT-1.19.4%20%E2%80%94%201.21.11-62b47a?style=for-the-badge&logo=minecraft&logoColor=white)
![Java](https://img.shields.io/badge/JAVA-17%2B-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Licença](https://img.shields.io/badge/LICEN%C3%87A-GPLv3-orange?style=for-the-badge&logo=gnu&logoColor=white)
![Status](https://img.shields.io/badge/STATUS-EM%20VALIDA%C3%87%C3%83O-yellow?style=for-the-badge)

<br>

<img src="https://raw.githubusercontent.com/Slimefun/Slimefun-Wiki/master/images/showcase1.png" width="600" style="border-radius:14px;box-shadow:0 8px 24px rgba(0,0,0,0.35)">

<br><br>

**[💬 DISCORD](https://discord.gg/hJwXe3tw4)** &nbsp;•&nbsp;
**[🐞 RELATAR UM PROBLEMA](https://github.com/davitremea1-ops/Slimefun4.5Core/issues)** &nbsp;•&nbsp;
**[📖 WIKI ORIGINAL](https://github.com/Slimefun/Slimefun4/wiki)** &nbsp;•&nbsp;
**[❤️ REPOSITÓRIO DESTE FORK](https://github.com/davitremea1-ops/Slimefun4.5Core)**

</div>

<br>

> **Slimefun** transforma seu servidor Minecraft em uma experiência semelhante a um modpack, **sem exigir que os jogadores instalem mods**. O projeto adiciona tecnologia, magia, máquinas automatizadas e centenas de itens personalizados, mantendo o cliente vanilla.
>
> ⚠️ Esta é uma versão modificada e não oficial do Slimefun. Ela não é afiliada nem representa a equipe responsável pelo projeto original.

---

## 📦 Compatibilidade pretendida

| Servidor | Java exigido pelo Paper | API usada na compilação |
|:---|:---:|:---:|
| Paper/Purpur 1.19.4 | Java 17 | Paper API 1.19.4 |
| Paper/Purpur 1.20.x | Java 21 | Paper API 1.19.4 |
| Paper/Purpur 1.21.x até 1.21.11 | Java 21 | Paper API 1.19.4 |

O projeto está sendo preparado para usar a API mais antiga como base e gerar bytecode Java 17, com o objetivo de manter um único JAR entre as versões listadas. A compatibilidade precisa ser confirmada pelos testes de compilação e por testes de inicialização em cada família de servidor.

**Importante:** isso não significa que todos os add-ons de terceiros funcionem em todas essas versões. Cada add-on precisa ser compatível com a API do Slimefun e com a versão do servidor em que será executado.

---

## 📊 Estado do fork

- **Base do projeto:** Slimefun4Core.
- **Versão mínima em validação:** Paper/Purpur 1.19.4.
- **Versão máxima em validação:** Paper/Purpur 1.21.11.
- **Versão do Java para compilar:** Java 17.
- **Idioma padrão:** Português do Brasil.
- **Licença:** GNU GPL v3.

> ⚠️ Não considere esta versão pronta para produção até que o CI passe e ela seja testada em servidores de cada versão-alvo. Faça backup antes de substituir o JAR em um servidor existente.

---

## 🌟 O que é o Slimefun?

*Um projeto conhecido na comunidade Minecraft desde 2013.*

| Recurso | Descrição |
|:---|:---|
| ⚙️ **Tecnologia avançada** | Reatores, geradores e redes de energia. |
| 🔮 **Magia e altares** | Runas, rituais e sistemas de fabricação mágicos. |
| 🏭 **Automação** | Máquinas automáticas e linhas de produção. |
| 🎒 **Centenas de itens** | Ferramentas, armaduras, máquinas e muito mais. |

---

## 🚀 Gerenciamento de add-ons

O fork inclui um gerenciador de add-ons para exibir e administrar complementos instalados manualmente ou gerenciados pela configuração remota.

### Recursos do gerenciador

| Recurso | Descrição |
|:---|:---|
| 🔄 **Lista dinâmica** | Exibe add-ons gerenciados e instalados manualmente. |
| 📊 **Resumo de estado** | Mostra quantos add-ons estão gerenciados, carregados ou fora do gerenciador. |
| 🧠 **Seleção de arquivos do GitHub** | Prioriza arquivos correspondentes ao nome do add-on. |
| 📖 **Detecção de add-ons** | Identifica metadados diretamente no `plugin.yml` do arquivo. |
| 🛡️ **Sincronização com alternativas** | Respeita as opções de fallback da sincronização remota. |

### Comandos de add-ons

```text
/sf addon list                 # Lista os add-ons instalados e gerenciados
/sf addon status               # Exibe o resumo e o estado da sincronização
/sf addon sync                 # Inicia uma sincronização manual
/sf addon enable <nome>        # Ativa um add-on gerenciado
/sf addon disable <nome>       # Desativa um add-on gerenciado
/sf addon update <nome>        # Atualiza um add-on específico
/sf addon download <nome>      # Baixa um add-on gerenciado
/sf addon remove <nome>        # Remove um add-on gerenciado
```

---

## 📸 Capturas de tela

As imagens abaixo são provenientes do projeto e da comunidade Slimefun original.

<div align="center">

<img src="https://raw.githubusercontent.com/Slimefun/Slimefun-Wiki/master/images/showcase1.png" width="270" style="border-radius:12px;margin:6px">
<img src="https://raw.githubusercontent.com/Slimefun/Slimefun-Wiki/master/images/showcase6.png" width="270" style="border-radius:12px;margin:6px">
<img src="https://raw.githubusercontent.com/Slimefun/Slimefun-Wiki/master/images/showcase5.png" width="270" style="border-radius:12px;margin:6px">

⚡ **Reatores e energia** &nbsp;&nbsp;|&nbsp;&nbsp; 🏭 **Fábricas automatizadas** &nbsp;&nbsp;|&nbsp;&nbsp; 🔮 **Magia e altares**

</div>

---

## 🌐 Comunidade e suporte

Entre na comunidade para conversar, acompanhar atualizações e relatar problemas.

- [Discord](https://discord.gg/hJwXe3tw4)
- [Relatar um problema neste fork](https://github.com/davitremea1-ops/Slimefun4.5Core/issues)
- [Wiki do Slimefun original](https://github.com/Slimefun/Slimefun4/wiki)
- [Código-fonte deste fork](https://github.com/davitremea1-ops/Slimefun4.5Core)

---

## 🤝 Créditos e licença

**Este é um fork não oficial e não possui vínculo com os desenvolvedores ou a equipe original do Slimefun.**

| Papel | Crédito |
|:---|:---|
| 👑 Criador do projeto original | [**TheBusyBiscuit**](https://github.com/TheBusyBiscuit) |
| 👥 Equipe do projeto original | Slimefun Team |
| 🛠️ Manutenção desta versão modificada | **R2Ksanu** |
| 📸 Imagens | Comunidade original do Slimefun |

Este projeto segue a **GNU General Public License v3.0 (GPLv3)**. As modificações e redistribuições devem respeitar os termos dessa licença e preservar os créditos exigidos. Consulte o arquivo [LICENSE](LICENSE) para ver o texto completo.

<div align="center">

### 💚 Obrigado por usar o SlimefunCore! 💚

*Uma experiência no estilo modpack, dentro do Minecraft vanilla.*

</div>
