## IDE

Intellij IDEA Community Edition 2021.1.1

---

## 操作說明

Player1
| 按鍵          | 動作      |
| ------------- |-----------|
| w             | 往上      |
| a             | 往左      |
| s             | 往下      |
| d             | 往右      |
| space         | 放炸彈    |

Player2
| 按鍵          | 動作      |
| ------------- |-----------|
| ↑             | 往上      |
| ←             | 往左      |
| ↓             | 往下      |
| →             | 往右      |
| enter         | 放炸彈    |

---

## 更新事項(2021/5/21)

1. 取代原本的tile，換上新的tile
2. 刪除用不到的class，並新增FloorTile和CornerTile以取代
3. 將原有地圖換上為新的tile

## 更新事項(2021/5/22)

1. 修正炸彈的放置位置(現在會準確的放在格子上)
2. 修正StaticEntity的放置位置(現在會準確的放在格子上)
3. 修改Explosion與Tile碰撞的判斷方式
4. 修改Creature對Explosion碰撞的判斷方式(暫時處理方式)
5. 取消Tile的bounding box(效果不好)

## 更新事項(2021/5/23)

1. 新增Item雛形
2. 取消Creature的血量設定
3. 現在可以移除被破壞的entity了
4. Entity新增onDestroy()，用來處理死亡時要做的事
5. 解決Explosion的碰撞問題(包括與Tile和Entity的碰撞)
6. 新增註解及修改部分註解

## 更新事項(2021/5/24)

1. 移除CornerTile class
2. 新增WallTile class
3. 替換原本的tile sheet
4. 移除部分不需要的判定
5. 縮短爆炸的持續時間
6. 新增穿透道具
7. 為道具加上初步的機率設定
8. 爆炸現在會破壞道具了
9. 新增基礎箱子物件配置

## 更新事項(2021/5/25)

1. 替換UnbreakableBox的texture
2. 降低加速道具的加速效果
3. 新增VerticalExplosion和HorizontalExplosion的texture
4. 新增4種Item的texture
5. 新增UI雛形

## 更新事項(2021/5/26)

1. 新增FontLoader class，用來讀取字體
2. 新增Sliver字體
3. 現在角色死亡時，會顯示另一個玩家獲勝的訊息
4. 新增遊戲結束的判定
5. 遊戲結束時，按下ESC可回到主選單(會有訊息提示)

## 更新事項(2021/5/28)

1. 完成主選單UI
2. 替角色及炸彈套上texture
3. 新增角色及炸彈動畫
4. 新增註解

## 更新事項(2021/5/29)

1. 現在能以讀檔的方式讀取地圖配置了
2. 修改地圖配置
3. 修改角色的bounding box

## 更新事項(2021/5/30)

1. 新增玩家2的texture
2. 修改道具掉落機率

## 更新事項(2021/6/6)

1. 為EntityManager的render做了例外處理，避免因為Exception中斷


---

## 待完成事項(重要事項基本上處理完畢)

1. ~~處理Explosion無法碰撞到Entity的問題~~
2. ~~將角色以及炸彈的texture替換上去~~
3. ~~處理Player與Tile的碰撞判定(精準度已經足夠，希望能換更有效的判斷方式)~~
4. ~~道具(火力強化、移動速度增加...)雛形~~
5. ~~設計地圖~~
6. 做選單(可能做也可能不會做)
7. 選地圖(可能會做也可能不會做)
8. ~~對戰規則設定~~
9. ~~UI雛形~~
10. ~~新增道具種類並設定生成機率~~
11. ~~幫道具套上texture~~
12. ~~設計主選單UI~~
13. ~~勝利訊息~~
14. 回選單後，地圖重製(暫時完成))

---

## 素材連結

1. 參考的Youtube教學:
   https://youtube.com/playlist?list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ
2. 遊戲內的Tile:
   https://free-game-assets.itch.io/free-industrial-zone-tileset-pixel-art
3. 遊戲內的字體:
   https://poppyworks.itch.io/silver
4. 幫了大忙的參考作品:
   https://github.com/blai30/bomberman-java