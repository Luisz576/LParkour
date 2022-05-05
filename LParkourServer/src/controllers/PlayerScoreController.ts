import express from "express"

const controller = {
    show: (req: express.Request, res: express.Response) => {
        const response: any = {}
        return res.json(response)
    },
    store: (req: express.Request, res: express.Response) => {
        const response: any = {}
        const { player_uuid, game_name, score } = req.body;
        if(player_uuid && game_name && score > 0){
            response.score = 1
        }else
            response.error = "Parameters not founded!"
        return res.json(response)
    }
}

module.exports = controller