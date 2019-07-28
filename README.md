# CosmoRun-JavaFX

### 首先
这个游戏是完全仿照Windows应用商店中的同名游戏(Cosmo Run)制作的，目的是学习javafx，本人不用来进行任何商业活动。

### 游戏截图
![preview](https://github.com/Nomango/CosmoRun-JavaFX/raw/master/images/preview.png)

### 我为什么做这款游戏
我是个Java半吊子，之前一直在学习C，从16年5月初开始转战javafx，这个游戏按照我自己对游戏开发的理解进行设计，并且是开源的，在文章最下方有下载链接。下面是游戏与原作的几张对比图（图下方是游戏原作）：

![1](https://github.com/Nomango/CosmoRun-JavaFX/raw/master/images/1.png)

不得不说javafx做界面确实好看，但是在做这个游戏时我还不会使用SceneBuilder，纯代码手工完成，代码一共三千多行，耗费了很大的时间和精力（绕了很多弯路。。。）。游戏框架很简单，基本结构如下图所示：

![2](https://github.com/Nomango/CosmoRun-JavaFX/raw/master/images/2.png)

游戏大背景和游戏界面层永远都在最底层，上层是设置界面、游戏结束界面、说明界面等等之间的互相切换。也就是说，不管你是在设置界面，还是在游戏说明界面，都能通过半透明的图层看到游戏界面。游戏界面都使用单例模式，储存在一个静态的Pane中，方便实现界面之间的切换。这样做最大的好处就是美观，我也是被这个游戏界面的美观和创意给吸引住了~
