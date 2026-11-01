<div align="center">

# ⛏️ SlimefunCore 4.5 ⛏️
### <sub>✨ VERSÃO MODIFICADA NÃO OFICIAL ✨</sub>
### <sub>Fork mantido por **R2Ksanu**</sub>

<br>

![Minecraft](https://img.shields.io/badge/MINECRAFT-1.21.11-62b47a?style=for-the-badge&logo=minecraft&logoColor=white)
![Java](https://img.shields.io/badge/JAVA-21-007396?style=for-the-badge&logo=openjdk&logoColor=white)
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

> **Slimefun** transforma seu servidor Minecraft em uma experiência semelhante a um modpack, sem exigir que os jogadores instalem mods. O projeto adiciona tecnologia, magia, máquinas automatizadas e centenas de itens personalizados.
>
> ⚠️ Esta é uma versão modificada e não oficial do Slimefun. Ela não é afiliada nem representa a equipe responsável pelo projeto original.

---

## 📦 Versão-alvo

| Componente | Versão |
|:---|:---|
| Minecraft | **1.21.11** |
| Servidor | **Paper / Purpur** |
| Java | **21** |
| API Paper usada na compilação | **1.21.11** |
| Idioma padrão | **Português do Brasil** |

Este fork é direcionado a servidores **Minecraft 1.21.11**. A meta de compatibilidade desta versão é manter add-ons existentes do ecossistema Slimefun funcionando sem exigir que o servidor volte para uma versão antiga do Minecraft.

## 🧩 Compatibilidade com add-ons

O trabalho de compatibilidade prioriza a API pública e os contratos binários que add-ons antigos esperam encontrar.

Entre os pontos preservados/restaurados estão:

- `SlimefunItemStack` continua sendo um `org.bukkit.inventory.ItemStack`, permitindo passá-lo diretamente a métodos Bukkit/Paper que recebem `ItemStack`.
- O método `SlimefunItemStack.lock()` continua disponível.
- O gancho protegido `SlimefunItem.isItemStackImmutable()` continua disponível para add-ons que o sobrescrevem.
- A exceção `WrongItemStackException` continua disponível.
- O método `clone()` mantém a ponte binária com retorno `ItemStack` exigida por código compilado contra a API antiga.

**Limite importante:** isso restaura compatibilidade em partes da API do Slimefun, mas não pode garantir que todo add-on funcione automaticamente. Add-ons que dependem de APIs internas removidas, NMS, versões próprias do Paper ou bibliotecas de terceiros precisam ser testados individualmente.

---

## 📊 Estado do fork

- **Servidor-alvo:** Paper/Purpur 1.21.11.
- **Java de compilação:** Java 21.
- **Compatibilidade de add-ons:** API legada em restauração e validação.
- **Tradução:** arquivos de idioma PT-BR completos em relação às chaves do idioma inglês.
- **Licença:** GNU GPL v3.

> ⚠️ Não considere esta versão pronta para produção até que o CI passe e os add-ons que você usa sejam testados em um servidor 1.21.11. Faça backup antes de substituir o JAR em um servidor existente.

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

Este projeto segue a **GNU General Public License v3.0 (GPLv3)**. As modificações e redistribuições devem respeitar os termos dessa licença e preservar os créditos exigidos. Consulte [LICENSE](LICENSE) para ver o texto completo.

<div align="center">

### 💚 Obrigado por usar o SlimefunCore! 💚

*Uma experiência no estilo modpack, dentro do Minecraft vanilla.*

</div>
