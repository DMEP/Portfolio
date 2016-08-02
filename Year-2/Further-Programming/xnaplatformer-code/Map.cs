using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;

namespace xnaplatformer
{
    public class Map
    {

        #region Variables

        public Layer layer;
        public Collision collision;

        string id;

        #endregion

        #region Properties

        public string ID
        {
            get { return id; }
        }

        #endregion

        #region Public Methods

        public void LoadContent(ContentManager content, Map map,  string mapID)
        {
            // load in Layer1 map
            layer = new Layer();
            collision = new Collision();
            id = mapID;

            layer.LoadContent(map, "Layer1");
            collision.LoadContent(content, mapID);
        }

        public void UnloadContent()
        {            
        }

        public void Update(GameTime gameTime)
        {
            layer.Update(gameTime);
        }

        public void UpdateCollision(ref Entity e)
        {
            layer.UpdateCollision(ref e);
        }

        public void Draw(SpriteBatch spriteBatch)
        {
            layer.Draw(spriteBatch);
        }

        #endregion

    }
}


