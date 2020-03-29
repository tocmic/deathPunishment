# deathPunishment
[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

## 功能列表：

<li>随机掉落n件物品</li>
<li>掉落金钱</li>
<li>掉落经验值</li>
<li>显示死亡标题</li>
<li>重生扣生命/饥饿</li>
<li>重生时给予药水效果</li>
<li>保护背包内的物品不掉落</li>
<p> </p>
<li>Drop items randomly</li>
<li>Drop money</li>
<li>Drop experience</li>
<li>Show death titles</li>
<li>Limit respawn health and/or food level</li>
<li>Cast potion effects on respawn</li>
<li>Prevent dropping of items</li>

## 安装||Install

<p>这个插件不需要任何前置插件！将其放进服务器地址/plugins里面重启即可</p>
<p>如果信息出现乱码，请使用本仓库/releases/内的语言文件</p>
<p>目前有</p>
<ul>
<li>英语（英国）--en_gb.lang</li>
<li>简体中文（中国）--zh_cn.lang</li>
<li>繁体中文（中国台湾）--zh_tw.lang</li>
</ul>
<p> </p>
<p>This plugin doesn't requires any other plugins.Please put it into server/plugins and restart the server</p>
<p>If random code occurs in game,please replace .lang files in plugins/deathPunishment/lang/ with the .lang files in this repository/releases/</p>
<p>Currently there are</p>
<ul>
<li>English（United Kingdom）--en_gb.lang</li>
<li>Simplified Chinese（China）--zh_cn.lang</li>
<li>Traditional Chinese（Chinese Taipei）--zh_tw.lang</li>
</ul>

## 配置文件||Configuration

```
Enabled: true                        #插件是否开启
version: "1.1"
SurvivalOnly: true                   #是否仅限生存玩家死亡后生效，强烈建议开启
#   输入""以禁用该条消息←还没做好
#   温馨提示：颜色代码的打法是 alt+1+6+7
#   leave it as "" to disable a message<---WIP
#   eg: deathworld:""
Messages:
  death: "§c你真菜，真的"                #死亡时显示的信息
  deathworld: "§c你原地去世于%s§f"       #死亡时显示的世界信息
  drop: "%s个物品已损失，鼠标悬浮以查看"       #掉落物品显示的提示，暂时不支持颜色代码
  respawn: "§c重生之后，你的身体大不如前……"   #重生时显示的信息
  exp: "§c%s级节操掉了一地"               #经验掉落提示
  money: "§c已掉落§f%s§c元金钱"          #金钱掉落提示
  loot: "§f%s§c元金币掉在了地上"           #掉落金钱时显示的文本
  lootname: "§c金币§f%s§c元"           #掉落金钱的物品显示名字
  lootpickup: "§c你拾得金币§f%s§c元"     #掉落金钱的物品显示名字
#   以下是死亡时显示的死亡屏幕,两个字符串全部留空以关闭
  title: "§c死"                       #死亡时标题栏显示的文本
  subtitle: "§c这就菜得离谱"             #死亡时副标题显示的文本
  fadein: 10                         #淡入时间
  stay: 100                          #持续时间
  fadeout: 40                        #淡出时间
#   物品掉落
#   注意格式，每个缩进是两个空格
DropSlots:
  Count: 20                          #掉落的物品格数量
  Enabled: true                      #是否启用掉落
  ProtectedSlots:                    #写保护的物品格，注意给掉落的物品格留出剩余
    - 39
    - 38
    - 37
    - 36
  ProtectedItems:                    #写保护的物品，物品ID可以用/dp what查看
    - DIAMOND
    - DIAMOND_SWORD
    - DIAMOND_PICKAXE
    - EMERALD
  ProtectedLores:                    #写保护的lore标签，%s代表玩家的名字
    - "灵魂绑定"
    - "§d绑定：%s"
  ProtectedEnchantments:
    - BINDING_CURSE
DropExp:
  Enabled: true                      #是否启用掉落经验值
  #经验掉落模式，Default:原版，Fixed:固定，Random:随机，Rate:按比例
  Mode: "Default"
  Default:
    Limit: 100                       #最多掉落多少点经验值，原版最多会掉落100点
  Fixed:                     
    Level: 5                         #等级
    SpawnOrb: true                   #是否生成经验球
    OrbPerLevel: 1                   #每损失一级掉落几个经验球
    OrbValue: 7                      #经验球的经验值
  Random:
    LevelMin: 5                      #最小等级
    LevelMax: 10                     #最大等级
    SpawnOrb: true                   #是否生成经验球
    OrbPerLevel: 1                   #每损失一级掉落几个经验球
    OrbValue: 7                      #每个经验球的经验值
  Rate:
    LevelRate: 0.5                   #玩家掉落已有等级的比例
    SpawnOrb: true                   #是否生成经验球
    OrbPerLevel: 1                   #每损失一级掉落几个经验球
    OrbValue: 7                      #每个经验球的经验值
DropMoney:
  Enabled: true                      #是否开启掉落金钱
  Loot: 0.5                          #掉落金钱的比率，0。0为不掉落
  LootIcon: GOLD_NUGGET              #掉落金钱的物品图标
  LootNameVisible: true              #掉落物名字是否可见
  DigitalAfterDot: 0                 #获得的金钱小数点后保留几位数
  #金钱掉落模式，Fixed:固定，Random:随机，Rate:按比例
  Mode: "Fixed"
  Fixed:
    Money: 10.0                      #钱数
  Random:
    MoneyMin: 1.0                    #最小钱数
    MoneyMax: 10.0                   #最大钱数
    DigitAfterDot: 0                 #小数点后保留位数
  Rate:
    MoneyRate: 0.5                   #掉落已有钱数的比例
    DigitAfterDot: 0                 #小数点后保留几位数
Health: 10.0                         #重生后扣除几点生命值
Hunger: 10                           #重生后扣除几点饥饿值，注意没有小数
Potions:                             #药水效果
  Enabled: true                      #是否启用药水效果
  1:                                 #单个效果编号，不能重复
    Type: HUNGER                     #药水效果ID，具体的列表可以去Bukkit官网查看
    Duration: 100                    #持续时间，以tick计算
    Amplifier: 1                     #增加等级，如果为0则为一级，每一点+1级
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
world:                               #世界的名称，注意大小写
  DropSlots:                         #以下项目与第21行至第55行完全一致
    Count: 20
    Enabled: true
    ProtectedSlots: []
    ProtectedItems: []
    ProtectedLores:                    #写保护的lore标签，%s代表玩家的名字
      - "灵魂绑定"
      - "§d绑定：%s"
    ProtectedEnchantments:
      - BINDING_CURSE
  DropExp:
    Enabled: true                      #是否启用掉落经验值
    #经验掉落模式，Default:原版，Fixed:固定，Random:随机，Rate:按比例
    Mode: "Default"
    Default:
      Limit: 100                       #最多掉落多少点经验值，原版最多会掉落100点
    Fixed:                     
      Level: 5                         #等级
      SpawnOrb: true                   #是否生成经验球
      OrbPerLevel: 1                   #每损失一级掉落几个经验球
      OrbValue: 7                      #经验球的经验值
    Random:
      LevelMin: 5                      #最小等级
      LevelMax: 10                     #最大等级
      SpawnOrb: true                   #是否生成经验球
      OrbPerLevel: 1                   #每损失一级掉落几个经验球
      OrbValue: 7                      #每个经验球的经验值
    Rate:
      LevelRate: 0.5                   #玩家掉落已有等级的比例
      SpawnOrb: true                   #是否生成经验球
      OrbPerLevel: 1                   #每损失一级掉落几个经验球
      OrbValue: 7                      #每个经验球的经验值
  DropMoney:
    Enabled: true                      #是否开启掉落金钱
    Loot: 0.5                          #掉落金钱的比率，0。0为不掉落
    LootIcon: GOLD_NUGGET              #掉落金钱的物品图标
    LootNameVisible: true              #掉落物名字是否可见，名字格式在“Messages”或语言文件内设定
    DigitalAfterDot: 0                 #获得的金钱小数点后保留几位数
    #金钱掉落模式，Fixed:固定，Random:随机，Rate:按比例
    Mode: "Fixed"
    Fixed:
      Money: 10.0                      #钱数
    Random:
      MoneyMin: 1.0                    #最小钱数
      MoneyMax: 10.0                   #最大钱数
      DigitAfterDot: 0                 #小数点后保留位数
    Rate:
      MoneyRate: 0.5                   #掉落已有钱数的比例
      DigitAfterDot: 0                 #小数点后保留几位数
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
