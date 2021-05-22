# Java_Bomberman_Game_For_Test

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

---

## 待完成事項
1. 處理Explosion無法碰撞到Player的問題(暫時用另一種方法解決了，但希望能用原本想的方式)
2. 將角色以及炸彈的sprite替換上去
3. 處理Player與Tile的碰撞判定(精準度已經足夠，希望能換更有效的判斷方式)
4. 道具(火力強化、移動速度增加...)
5. 設計地圖
6. 做選單
7. 選地圖(可能會做也可能不會做)
8. 對戰規則設定 

---

## 素材連結
1. 參考的Youtube教學:
https://youtube.com/playlist?list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ
2. 遊戲內的Tile
https://free-game-assets.itch.io/free-industrial-zone-tileset-pixel-art