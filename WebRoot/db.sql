--
-- 数据库: `yzdbforjsp`
--
CREATE DATABASE IF NOT EXISTS `yzdbforjsp` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `yzdbforjsp`;

-- --------------------------------------------------------

--
-- 表的结构 `12058_album`
--

DROP TABLE IF EXISTS `12058_album`;
CREATE TABLE IF NOT EXISTS `12058_album` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NickName` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `Album` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `PhotoName` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `Description` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=22 ;

--
-- 转存表中的数据 `12058_album`
--

INSERT INTO `12058_album` (`Id`, `NickName`, `Album`, `PhotoName`, `Description`) VALUES
(20, 'yz', '我的RA3', '1393146621308.png', '此照片没有描述信息'),
(21, 'yz', '我的RA3', '1393146646736.png', '鬼王X');

-- --------------------------------------------------------

--
-- 表的结构 `12058_album_list`
--

DROP TABLE IF EXISTS `12058_album_list`;
CREATE TABLE IF NOT EXISTS `12058_album_list` (
  `NickName` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `Album` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `Preview` varchar(150) COLLATE utf8_unicode_ci DEFAULT 'album/default.jpg',
  PRIMARY KEY (`NickName`,`Album`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `12058_album_list`
--

INSERT INTO `12058_album_list` (`NickName`, `Album`, `Preview`) VALUES
('yz', '我的RA3', 'album/1393146646736f.png');

-- --------------------------------------------------------

--
-- 表的结构 `12058_friends`
--

DROP TABLE IF EXISTS `12058_friends`;
CREATE TABLE IF NOT EXISTS `12058_friends` (
  `Left` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `Right` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Left`,`Right`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `12058_friends`
--

INSERT INTO `12058_friends` (`Left`, `Right`) VALUES
('yanzhen0923', 'yz'),
('yanzhen0923', '颜震'),
('yz', 'yanzhen0923'),
('yz', '光绪'),
('yz', '李商隐'),
('yz', '爱新觉罗玄烨'),
('yz', '白居易'),
('yz', '雍正'),
('yz', '韩愈'),
('yz', '顺治'),
('yz', '颜震'),
('光绪', 'yz'),
('我有一头小毛驴', '颜震'),
('李商隐', 'yz'),
('李商隐', '白居易'),
('江晨阳', '颜震'),
('爱新觉罗玄烨', 'yz'),
('白居易', 'yz'),
('白居易', '李商隐'),
('白居易', '颜震'),
('雍正', 'yz'),
('韩愈', 'yz'),
('顺治', 'yz'),
('颜震', 'yanzhen0923'),
('颜震', 'yz'),
('颜震', '我有一头小毛驴'),
('颜震', '江晨阳'),
('颜震', '白居易');

-- --------------------------------------------------------

--
-- 表的结构 `12058_info_f`
--

DROP TABLE IF EXISTS `12058_info_f`;
CREATE TABLE IF NOT EXISTS `12058_info_f` (
  `Sender` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `Receiver` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Sender`,`Receiver`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `12058_info_f`
--

INSERT INTO `12058_info_f` (`Sender`, `Receiver`) VALUES
('光绪', '爱新觉罗玄烨'),
('颜震', 'jcy');

-- --------------------------------------------------------

--
-- 表的结构 `12058_info_m`
--

DROP TABLE IF EXISTS `12058_info_m`;
CREATE TABLE IF NOT EXISTS `12058_info_m` (
  `Sender` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `Receiver` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Sender`,`Receiver`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `12058_msg`
--

DROP TABLE IF EXISTS `12058_msg`;
CREATE TABLE IF NOT EXISTS `12058_msg` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Sender` char(150) COLLATE utf8_unicode_ci NOT NULL,
  `Receiver` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `Content` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `Time` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=30 ;

--
-- 转存表中的数据 `12058_msg`
--

INSERT INTO `12058_msg` (`Id`, `Sender`, `Receiver`, `Content`, `Time`) VALUES
(1, '颜震', 'yz', '我有一头小毛驴', '2014-02-19 16:12:53'),
(2, 'yz', '颜震', '你好啊', '2014-02-19 16:37:13'),
(3, 'yz', '颜震', '啊哈哈', '2014-02-19 16:37:21'),
(4, '颜震', '江晨阳', '呵呵呵', '2014-02-19 16:48:40'),
(5, '我有一头小毛驴', 'yz', '咋俩认识么呵呵？', '2014-02-19 18:32:19'),
(6, '李商隐', '白居易', '相见时难别亦难，东风无力百花残。\r\n\r\n春蚕到死丝方尽，蜡炬成灰泪始干。\r\n\r\n晓镜但愁云鬓改，夜吟应觉月光寒。\r\n\r\n蓬山此去无多路，青鸟殷勤为探看。', '2014-02-20 09:24:54'),
(7, '李商隐', '白居易', '锦瑟无端五十弦，一弦一柱思华年。\r\n　　庄生晓梦迷蝴蝶，望帝春心托杜鹃。\r\n　　沧海月明珠有泪，蓝田日暖玉生烟。\r\n　　此情可待成追忆，只是当时已惘然。', '2014-02-20 09:26:14'),
(8, '李商隐', '白居易', '　向晚意不适，驱车登古原。夕阳无限好，只是近黄昏。', '2014-02-20 09:26:58'),
(9, '李商隐', '白居易', '　君问归期未有期，巴山夜雨涨秋池。\r\n　　何当共剪西窗烛，却话巴山夜雨时。', '2014-02-20 09:27:08'),
(10, '李商隐', '白居易', '昨夜星辰昨夜风，画楼西畔桂堂东。\r\n　　身无彩凤双飞翼，心有灵犀一点通。\r\n　　隔座送钩春酒暖，分曹射覆蜡灯红。\r\n　　嗟余听鼓应官去，走马兰台类断蓬。\r\n　　闻道阊门萼绿华，昔年相望抵天涯。', '2014-02-20 09:28:10'),
(11, '白居易', 'yz', '浔阳江头夜送客，枫叶荻花秋瑟瑟。\r\n\r\n主人下马客在船，举酒欲饮无管弦。\r\n\r\n醉不成欢惨将别，别时茫茫江浸月。\r\n\r\n忽闻水上琵琶声，主人忘归客不发。\r\n\r\n寻声暗问弹者谁？琵琶声停欲语迟。\r\n\r\n移船相近邀相见，添酒回灯重开宴。\r\n\r\n千呼万唤始出来，犹抱琵琶半遮面。', '2014-02-20 09:29:34'),
(12, '白居易', 'yz', '转轴拨弦三两声，未成曲调先有情。\r\n\r\n弦弦掩抑声声思，似诉平生不得志（意）。\r\n\r\n低眉信手续续弹，说尽心中无限事。\r\n\r\n轻拢慢捻抹复挑，初为《霓裳》后《六幺》（《六幺》又作《绿腰》）。\r\n\r\n大弦嘈嘈如急雨，小弦切切如私语。\r\n\r\n嘈嘈切切错杂弹，大珠小珠落玉盘。\r\n\r\n间关莺语花底滑，幽咽泉流冰下难。\r\n\r\n冰泉冷涩弦凝绝，凝绝不通声暂歇。\r\n\r\n别有幽愁暗恨生，此时无声胜有声。', '2014-02-20 09:30:30'),
(13, '白居易', 'yz', '银瓶乍破水浆迸，铁骑突出刀枪鸣。\r\n\r\n曲终收拨当心画，四弦一声如裂帛。\r\n\r\n东船西舫悄无言，唯见江心秋月白。', '2014-02-20 09:30:49'),
(14, '白居易', 'yz', '沉吟放拨插弦中，整顿衣裳起敛容。\r\n\r\n自言本是京城女，家在虾蟆陵下住。\r\n\r\n十三学得琵琶成，名属教坊第一部。\r\n\r\n曲罢曾教善才服，妆成每被秋娘妒。\r\n\r\n五陵年少争缠头，一曲红绡不知数。\r\n\r\n钿头云篦击节碎，血色罗裙翻酒污。\r\n\r\n今年欢笑复明年，秋月春风等闲度。', '2014-02-20 09:31:26'),
(15, '白居易', 'yz', '弟走从军阿姨死，暮去朝来颜色故。\r\n\r\n门前冷落鞍马稀，老大嫁作商人妇。\r\n\r\n商人重利轻别离，前月浮梁买茶去。\r\n\r\n去来江口守空船，绕船月明江水寒。\r\n\r\n夜深忽梦少年事，梦啼妆泪红阑干。', '2014-02-20 09:32:10'),
(16, '白居易', 'yz', '我闻琵琶已叹息，又闻此语重唧唧。\r\n\r\n同是天涯沦落人，相逢何必曾相识！\r\n\r\n我从去年辞帝京，谪居卧病浔阳城。\r\n\r\n浔阳地僻无音乐，终岁不闻丝竹声。\r\n\r\n住近湓江地低湿，黄芦苦竹绕宅生。\r\n\r\n其间旦暮闻何物？杜鹃啼血猿哀鸣。\r\n\r\n春江花朝秋月夜，往往取酒还独倾。\r\n\r\n岂无山歌与村笛？呕哑嘲哳难为听。\r\n\r\n今夜闻君琵琶语，如听仙乐耳暂明。\r\n\r\n莫辞更坐弹一曲，为君翻作《琵琶行》。', '2014-02-20 09:32:41'),
(17, '白居易', 'yz', '感我此言良久立，却坐促弦弦转急。\r\n\r\n凄凄不似向前声，满座重闻皆掩泣。\r\n\r\n座中泣下谁最多？江州司马青衫湿。[', '2014-02-20 09:32:59'),
(18, '白居易', '李商隐', '汉皇重色思倾国，御宇多年求不得。杨家有女初长成，养在深闺人未识。\r\n 天生丽质难自弃，一朝选在君王侧。回眸一笑百媚生，六宫粉黛无颜色。\r\n 春寒赐浴华清池，温泉水滑洗凝脂。侍儿扶起娇无力，始是新承恩泽时。\r\n 云鬓花颜金步摇，芙蓉帐暖度春宵。春宵苦短日高起，从此君王不早朝。\r\n 承欢侍宴无闲暇，春从春游夜专夜。后宫佳丽三千人，三千宠爱在一身。', '2014-02-20 09:36:18'),
(19, '白居易', 'yz', '金屋妆成娇侍夜，玉楼宴罢醉和春。姊妹弟兄皆列土，可怜光彩生门户。\r\n 遂令天下父母心，不重生男重生女。骊宫高处入青云，仙乐风飘处处闻。\r\n 缓歌谩舞凝丝竹，尽日君王看不足。渔阳鼙鼓动地来，惊破霓裳羽衣曲。\r\n 九重城阙烟尘生，千乘万骑西南行。翠华摇摇行复止，西出都门百余里。\r\n 六军不发无奈何，宛转蛾眉马前死。花钿委地无人收，翠翘金雀玉搔头。\r\n 君王掩面救不得，回看血泪相和流。黄埃散漫风萧索，云栈萦纡登剑阁。\r\n 峨嵋山下少人行，旌旗无光日色薄。蜀江水碧蜀山青，圣主朝朝暮暮情。', '2014-02-20 09:37:30'),
(20, '白居易', 'yz', '行宫见月伤心色，夜雨闻铃肠断声。天旋地转回龙驭，到此踌躇不能去。\r\n 马嵬坡下泥土中，不见玉颜空死处。君臣相顾尽沾衣，东望都门信马归。\r\n 归来池苑皆依旧，太液芙蓉未央柳。芙蓉如面柳如眉，对此如何不泪垂。\r\n 春风桃李花开日，秋雨梧桐叶落时。西宫南内多秋草，落叶满阶红不扫。\r\n 梨园弟子白发新，椒房阿监青娥老。夕殿萤飞思悄然，孤灯挑尽未成眠。\r\n 迟迟钟鼓初长夜，耿耿星河欲曙天。鸳鸯瓦冷霜华重，翡翠衾寒谁与共。\r\n 悠悠生死别经年，魂魄不曾来入梦。', '2014-02-20 09:38:52'),
(21, '白居易', 'yz', '临邛道士鸿都客，能以精诚致魂魄。\r\n 为感君王辗转思，遂教方士殷勤觅。排空驭气奔如电，升天入地求之遍。\r\n 上穷碧落下黄泉，两处茫茫皆不见。忽闻海上有仙山，山在虚无缥渺间。\r\n 楼阁玲珑五云起，其中绰约多仙子。中有一人字太真，雪肤花貌参差是。', '2014-02-20 09:39:57'),
(22, '白居易', 'yz', '金阙西厢叩玉扃，转教小玉报双成。闻道汉家天子使，九华帐里梦魂惊。\r\n 揽衣推枕起徘徊，珠箔银屏迤逦开。云鬓半偏新睡觉，花冠不整下堂来。\r\n 风吹仙袂飘飘举，犹似霓裳羽衣舞。玉容寂寞泪阑干，梨花一枝春带雨。\r\n 含情凝睇谢君王，一别音容两渺茫。昭阳殿里恩爱绝，蓬莱宫中日月长。\r\n 回头下望人寰处，不见长安见尘雾。惟将旧物表深情，钿合金钗寄将去。\r\n 钗留一股合一扇，钗擘黄金合分钿。但教心似金钿坚，天上人间会相见。\r\n 临别殷勤重寄词，词中有誓两心知。七月七日长生殿，夜半无人私语时。\r\n 在天愿作比翼鸟，在地愿为连理枝。天长地久有时尽，此恨绵绵无绝期。', '2014-02-20 09:41:20'),
(23, '白居易', '李商隐', '金屋妆成娇侍夜，玉楼宴罢醉和春。姊妹弟兄皆列土，可怜光彩生门户。 遂令天下父母心，不重生男重生女。骊宫高处入青云，仙乐风飘处处闻。 缓歌谩舞凝丝竹，尽日君王看不足。渔阳鼙鼓动地来，惊破霓裳羽衣曲。 九重城阙烟尘生，千乘万骑西南行。翠华摇摇行复止，西出都门百余里。 六军不发无奈何，宛转蛾眉马前死。花钿委地无人收，翠翘金雀玉搔头。 君王掩面救不得，回看血泪相和流。黄埃散漫风萧索，云栈萦纡登剑阁。 峨嵋山下少人行，旌旗无光日色薄。蜀江水碧蜀山青，圣主朝朝暮暮情。\r\n', '2014-02-20 09:42:35'),
(24, '白居易', '李商隐', '行宫见月伤心色，夜雨闻铃肠断声。天旋地转回龙驭，到此踌躇不能去。 马嵬坡下泥土中，不见玉颜空死处。君臣相顾尽沾衣，东望都门信马归。 归来池苑皆依旧，太液芙蓉未央柳。芙蓉如面柳如眉，对此如何不泪垂。 春风桃李花开日，秋雨梧桐叶落时。西宫南内多秋草，落叶满阶红不扫。 梨园弟子白发新，椒房阿监青娥老。夕殿萤飞思悄然，孤灯挑尽未成眠。 迟迟钟鼓初长夜，耿耿星河欲曙天。鸳鸯瓦冷霜华重，翡翠衾寒谁与共。 悠悠生死别经年，魂魄不曾来入梦。', '2014-02-20 09:42:55'),
(25, '白居易', '李商隐', '临邛道士鸿都客，能以精诚致魂魄。 为感君王辗转思，遂教方士殷勤觅。排空驭气奔如电，升天入地求之遍。 上穷碧落下黄泉，两处茫茫皆不见。忽闻海上有仙山，山在虚无缥渺间。 楼阁玲珑五云起，其中绰约多仙子。中有一人字太真，雪肤花貌参差是。', '2014-02-20 09:43:06'),
(26, '白居易', '李商隐', '金阙西厢叩玉扃，转教小玉报双成。闻道汉家天子使，九华帐里梦魂惊。 揽衣推枕起徘徊，珠箔银屏迤逦开。云鬓半偏新睡觉，花冠不整下堂来。 风吹仙袂飘飘举，犹似霓裳羽衣舞。玉容寂寞泪阑干，梨花一枝春带雨。 含情凝睇谢君王，一别音容两渺茫。昭阳殿里恩爱绝，蓬莱宫中日月长。 回头下望人寰处，不见长安见尘雾。惟将旧物表深情，钿合金钗寄将去。 钗留一股合一扇，钗擘黄金合分钿。但教心似金钿坚，天上人间会相见。 临别殷勤重寄词，词中有誓两心知。七月七日长生殿，夜半无人私语时。 在天愿作比翼鸟，在地愿为连理枝。天长地久有时尽，此恨绵绵无绝期。', '2014-02-20 09:43:18'),
(27, '李商隐', '白居易', '海外徒闻更九州，他生未卜此生休。\r\n空闻虎旅传宵柝，无复鸡人报晓筹。\r\n此日六军同驻马，当时七夕笑牵牛。\r\n如何四纪为天子，不及卢家有莫愁。 ', '2014-02-20 09:44:51'),
(28, 'yz', '白居易', '你是白居易么', '2014-02-20 19:50:49'),
(29, '光绪', 'yz', '哈哈', '2014-02-20 21:20:58');

-- --------------------------------------------------------

--
-- 表的结构 `12058_news`
--

DROP TABLE IF EXISTS `12058_news`;
CREATE TABLE IF NOT EXISTS `12058_news` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NickName` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `Time` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Content` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `Authority` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=52 ;

--
-- 转存表中的数据 `12058_news`
--

INSERT INTO `12058_news` (`Id`, `NickName`, `Time`, `Content`, `Authority`) VALUES
(1, 'yz', '2014-02-19 15:09:18', 'yz的好友可见状态', 2),
(2, 'yz', '2014-02-19 16:53:58', '我去啊哈哈哈', 0),
(3, '江晨阳', '2014-02-19 16:54:18', '我叫江晨阳', 0),
(4, 'yz', '2014-02-19 20:35:54', '私有的', 1),
(6, 'yz', '2014-02-19 20:36:30', '公开的', 0),
(7, 'yz', '2014-02-19 20:36:36', '好友的', 2),
(8, 'yz', '2014-02-19 20:37:08', '后面一条应该看不见了吧', 0),
(9, 'yz', '2014-02-19 20:37:22', '再试试', 0),
(10, 'yz', '2014-02-19 21:13:07', '哈哈1', 0),
(11, 'yz', '2014-02-19 21:13:10', '哈哈2', 0),
(12, 'yz', '2014-02-19 21:13:13', '哈哈3', 0),
(13, 'yz', '2014-02-19 21:13:18', '哈哈4', 0),
(14, 'yz', '2014-02-19 21:13:21', '哈哈5', 0),
(15, 'yz', '2014-02-19 21:13:26', '哈哈6', 0),
(16, 'yz', '2014-02-19 21:13:30', '哈哈7', 0),
(17, 'yz', '2014-02-19 21:13:33', '哈哈8', 0),
(18, 'yz', '2014-02-19 21:13:37', '哈哈9', 0),
(19, 'yz', '2014-02-19 21:13:40', '哈哈10', 0),
(20, 'yz', '2014-02-19 21:15:28', '呵呵1', 2),
(21, 'yz', '2014-02-19 21:15:35', '呵呵2', 2),
(22, 'yz', '2014-02-19 21:15:39', '呵呵3', 2),
(23, 'yz', '2014-02-19 21:15:43', '呵呵4', 2),
(24, 'yz', '2014-02-19 21:15:46', '呵呵5', 0),
(26, 'yz', '2014-02-19 21:15:57', '呵呵7', 2),
(27, 'yz', '2014-02-19 21:16:02', '呵呵8', 2),
(36, '颜震', '2014-02-19 22:38:19', '好友可见呵呵呵', 2),
(38, '颜震', '2014-02-20 15:42:45', '好友可见第一条', 2),
(39, '颜震', '2014-02-20 15:42:56', '好友可见第二条', 2),
(40, '颜震', '2014-02-20 15:43:04', '好友可见第三条', 2),
(41, '颜震', '2014-02-20 15:43:10', '好友可见第四条', 2),
(42, '颜震', '2014-02-20 15:43:19', '好友可见第五条', 2),
(43, '颜震', '2014-02-20 15:43:26', '好友可见第六条', 2),
(44, '颜震', '2014-02-20 15:43:36', '好友可见第七条', 2),
(45, '颜震', '2014-02-20 15:43:41', '好友可见第八条', 2),
(46, '颜震', '2014-02-20 15:43:49', '好友可见第九条', 2),
(47, '颜震', '2014-02-20 15:44:04', '好友可见第十条', 2),
(48, '颜震', '2014-02-20 15:44:12', '好友可见第十一条', 2),
(50, '光绪', '2014-02-20 19:55:25', '好像有点问题额', 0),
(51, '光绪', '2014-02-20 21:20:41', '这回好像没啥问题了', 0);

-- --------------------------------------------------------

--
-- 表的结构 `12058_users`
--

DROP TABLE IF EXISTS `12058_users`;
CREATE TABLE IF NOT EXISTS `12058_users` (
  `NickName` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `Pass` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `RealName` varchar(50) COLLATE utf8_unicode_ci DEFAULT '匿名',
  `Dorm` varchar(50) COLLATE utf8_unicode_ci DEFAULT '地球',
  `Birthday` varchar(10) COLLATE utf8_unicode_ci DEFAULT '1900-1-1',
  `Email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Authority` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `Avatar` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'avatar/default.png',
  PRIMARY KEY (`NickName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `12058_users`
--

INSERT INTO `12058_users` (`NickName`, `Pass`, `RealName`, `Dorm`, `Birthday`, `Email`, `Authority`, `Avatar`) VALUES
('jcy', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'jcy@bit.edu.cn', '0', 'avatar/1392805663938.png'),
('yanzhen0923', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'yanzhen0923@outlook.com', '0', 'avatar/1392805771321.png'),
('yz', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '颜震', '新1#613', '1992-9-23', 'yanzhen0923@163.com', '0', 'avatar/1393145685378.png'),
('乾隆', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'qianlong@qing.dynasty', '0', 'avatar/default.png'),
('光绪', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '老子就是光绪', '紫禁城', '1985-1-1', 'guangxu@qing.dynasty', '0', 'avatar/1392897268490.png'),
('典韦', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'dianwei@wei.guo', '0', 'avatar/default.png'),
('刘禹锡', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'liuyuxi@tang.dynasty', '0', 'avatar/default.png'),
('同治', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'tongzhi@qing.dynasty', '0', 'avatar/default.png'),
('咸丰', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'xianfeng@qing.dynasty', '0', 'avatar/default.png'),
('嘉庆', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'jiaqing@qing.dynasty', '0', 'avatar/default.png'),
('张辽', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'zhangliao@wei.guo', '0', 'avatar/default.png'),
('我有一头小毛驴', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', '0800314@163.com', '0', 'avatar/default.png'),
('李商隐', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'lishangyin@tang.dynasty', '0', 'avatar/1392858553582.png'),
('李清照', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'liqingzhao@song.dynasty', '0', 'avatar/default.png'),
('李煜', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'liyu@song.dynasty', '0', 'avatar/default.png'),
('李白', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'libai@tang.dynasty', '0', 'avatar/default.png'),
('杜牧', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'dumu@tang.dynasty', '0', 'avatar/default.png'),
('杜甫', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'dufu@tang.dynasty', '0', 'avatar/default.png'),
('柳永', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'liuyong@song.dynasty', '0', 'avatar/default.png'),
('江晨阳', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'jcy@bit.edu.cn', '0', 'avatar/1392805519844.png'),
('溥仪', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'puyi@xin.zhongguo', '0', 'avatar/default.png'),
('爱新觉罗玄烨', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'aixinjueluoxuanye@qing.dynasty', '0', 'avatar/default.png'),
('王勃', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'wangbo@tang.dynasty', '0', 'avatar/default.png'),
('白居易', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'baijuyi@wantang.dynasty', '0', 'avatar/1392860837303.png'),
('苏轼', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'sushi@song.dynasty', '0', 'avatar/default.png'),
('许褚', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'xuchu@wei.guo', '0', 'avatar/default.png'),
('辛弃疾', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'xinqiji@song.dynasty', '0', 'avatar/default.png'),
('道光', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'daoguang@qing.dynasty', '0', 'avatar/default.png'),
('雍正', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'yongzheng@qing.dynasty', '0', 'avatar/default.png'),
('韩愈', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'hanyu@tang.dynasty', '0', 'avatar/default.png'),
('顺治', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '匿名', '地球', '1900-1-1', 'shunzhi@qing.dynasty', '0', 'avatar/default.png'),
('颜震', 'bf9f8d1f05dc08cc3b02e8fcf2c2ba57', '好像是颜震', '613', '1985-1-1', 'yanzhen0923@gmail.com', '0', 'avatar/1392805631853.png');
