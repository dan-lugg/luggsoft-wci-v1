package com.luggsoft.mtg

interface GameAction<TObject : GameObject> : (TObject) -> Unit

interface GameObject
{
    var onEnter: GameAction<GameObject>
    var onLeave: GameAction<GameObject>
    
    var onExert: GameAction<GameObject>
    var onDefer: GameAction<GameObject>
}

interface ArtifactGameObject : GameObject

interface CreatureGameObject : GameObject
{
    var onAttack: GameAction<CreatureGameObject>
    var onDefend: GameAction<CreatureGameObject>
}
