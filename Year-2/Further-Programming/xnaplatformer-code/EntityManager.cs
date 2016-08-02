using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;

namespace xnaplatformer
{
    public class EntityManager
    {

        #region Variables

        List<Entity> entities;
        FileManager fileManager;
        InputManager input;

        #endregion

        #region Properties

        public List<Entity> Entities
        {
            get { return entities; }
        }

        #endregion

        #region Public Methods

        public void LoadContent(string entityType, ContentManager Content, string fileName, string identifier, InputManager input)
        {
            this.input = input;
            entities = new List<Entity>();
            fileManager = new FileManager();

            if (identifier == string.Empty)
                fileManager.LoadContent(fileName, "");
            else
                fileManager.LoadContent(fileName, identifier);

            for (int i = 0; i < fileManager.Attributes.Count; i++)
            {
                Type newClass = Type.GetType("xnaplatformer." + entityType);
                entities.Add((Entity)Activator.CreateInstance(newClass));
                entities[i].LoadContent(Content, fileManager.Attributes[i], fileManager.Contents[i], this.input);
            }
        }

        public void UnloadContent()
        {
            for(int i = 0; i < entities.Count; i++)
                entities[i].UnloadContent();
        }

        public void Update(GameTime gameTime, Map map)
        {
            for (int i = 0; i < entities.Count; i++)
                entities[i].Update(gameTime, input, map.collision, map.layer);
        }

        public void EntityCollision(EntityManager E2)
        {
            foreach (Entity e in entities)
            {
                foreach (Entity e2 in E2.Entities)
                {
                    if (e.Rect.Intersects(e2.Rect))
                        e.OnCollision(e2);

                }
            }
        }

        public void Draw(SpriteBatch spriteBatch)
        {
            for (int i = 0; i < entities.Count; i++)
                entities[i].Draw(spriteBatch);
        }

        #endregion

    }
}