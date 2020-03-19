# deathPunishment
[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

## 功能列表：

<li>随机掉落n件物品</li>
<li>掉落经验值</li>
<li>显示死亡标题</li>
<li>重生扣生命/饥饿</li>
<li>重生时给予药水效果</li>

## With this plugin,you can

<li>Drop items randomly</li>
<li>Drop experience</li>
<li>Show death titles</li>
<li>Limit respawn health and/or food level</li>
<li>Cast potion effects on respawn</li>

## 安装||Install

这个插件不需要任何前置插件！将其放进服务器地址/plugins里面重启即可
如果信息出现乱码，请使用本仓库/lang/内的语言文件
目前仅有
英语（英国）--en_uk.lang
简体中文（中国）--zh_cn.lang
繁体中文（中国台湾）--zh_tw.lang
可以使用

## 配置文件||Configuration

```
Enabled: true
SurvivalOnly: true
#输入""以禁用该条消息
#温馨提示：颜色代码的打法是 alt+1+6+7
#leave it as "" to disable a message
#eg: deathworld:""
Messages:
  death: "§c你真菜，真的"
  deathworld: "§c你原地去世于§f"
  drop: "个物品已损失，鼠标悬浮以查看"
  respawn: "§c重生之后，你的身体大不如前……"
  exp: "§c级节操掉了一地"
  #以下是死亡时显示的死亡屏幕,两个字符串全部留空以关闭
  title: "§c死"
  subtitle: "§c这就菜得离谱"
  fadein: 10
  stay: 100
  fadeout: 40
DropSlots:
  Count: 20
  Enabled: true
  ProtectedSlots: 
    - 39
    - 38
    - 37
    - 36
  ProtectedItems: 
    - DIAMOND
    - DIAMOND_SWORD
    - DIAMOND_PICKAXE
    - EMERALD
DropExp:
  Level: 5
  Enabled: true
  SpawnOrb: true
  OrbPerLevel: 2
  OrbValue: 5
Health: 10.0
Hunger: 10
Potions:
  Enabled: true
  1:
    Type: HUNGER
    Duration: 100
    Amplifier: 1
  2:
    Type: WEAKNESS
    Duration: 100
    Amplifier: 1
  3:
    Type: SLOW_DIGGING
    Duration: 100
    Amplifier: 1
#在下面编辑世界设定
#worlds settings goes herevvv
world:
  DropSlots:
    Count: 20
    Enabled: true
    ProtectedSlots: []
    ProtectedItems: []
  DropExp:
    Level: 5
    Enabled: true
    SpawnOrb: true
    OrbPerLevel: 1
    OrbValue: 5
  Health: 10.0
  Hunger: 10
  Potions:
    Enabled: true
    1:
      Type: HUNGER
      Duration: 1200
      Amplifier: 2
    2:
      Type: WEAKNESS
      Duration: 1200
      Amplifier: 1
    3:
      Type: SLOW_DIGGING
      Duration: 1200
      Amplifier: 2
    4:
      Type: SLOW
      Duration: 1200
      Amplifier: 1
    5:
      Type: CONFUSION
      Amplifier: 1
      Duration: 600
```
