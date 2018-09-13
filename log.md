# 当player在移动过程中可能发生的：

## 不能移动的：

- Wall  (当遇到wall时不能移动)
- Sword （如果player已经拿到了一个sword， 当再遇到sword时不能移动，也不能拾取）
- door （如果door是没有开的状态时，将不能穿过）
- 如果碰到以上情况return false

## Die：

- bloom 的爆炸范围以内
- pit （当没有石头的时候或者没有飞行药水时）
- enemy （没有武器，以及没有无敌药水的时候）
- set alive = false

## Pick：

- key （only 1）
- bloom （无限制）
- sword （only one）
- arrow （无限制）
- treasure（无限制）

## Use directly：

- invincibility（无敌药水，有时间限制）
- hover （飞过pit，直到游戏结束或者player死亡）



可以找一个pre，把当前坐标的值复制下来，然后如果pre == Object.opendoor, 或者什么东西，就把当前的position设置为pre