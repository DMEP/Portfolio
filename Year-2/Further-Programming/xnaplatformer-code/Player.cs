using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace xnaplatformer
{
    public class Player : Entity
    {

        #region Variables

        float jumpSpeed = 1000f;
        FileManager fileManager;

        #endregion

        #region Public Methods

        public override void LoadContent(ContentManager content, List<string> attributes, List<string> contents, InputManager input)
        {
            base.LoadContent(content, attributes, contents, input);
            string[] attribute = { "PlayerPosition" };
            string[] ccontent = { position.X.ToString() + "," + position.Y.ToString() };

            fileManager = new FileManager();
            //filename will need to be changed if file location changed
            fileManager.SaveContent("F:/college/Year 2/CO5045 - C#/xnaplatformer/xnaplatformer/xnaplatformer/Load/Maps/Map1.txt", attribute, ccontent, "");
        }
       
        public override void UnloadContent()
        {
            base.UnloadContent();
            moveAnimation.UnloadContent();
        }

        public override void Update(GameTime gameTime, InputManager input, Collision col, Layer layer)
        {
            //player velocity
            base.Update(gameTime, input, col, layer);
            moveAnimation.DrawColor = Color.White;
            moveAnimation.IsActive = true;
            if (input.KeyDown(Keys.Right, Keys.D))
            {
                moveAnimation.CurrentFrame = new Vector2(moveAnimation.CurrentFrame.X, 2);
                velocity.X = moveSpeed * (float)gameTime.ElapsedGameTime.TotalSeconds; 
            }
            else if (input.KeyDown(Keys.Left, Keys.A))
            {
                moveAnimation.CurrentFrame = new Vector2(moveAnimation.CurrentFrame.X, 1);
                velocity.X = -moveSpeed * (float)gameTime.ElapsedGameTime.TotalSeconds; 
            }
            else
            {
                moveAnimation.IsActive = false;
                velocity.X = 0;
            }
            if (input.KeyDown(Keys.Up, Keys.W) && !activateGravity)
            {
                velocity.Y = -jumpSpeed * (float)gameTime.ElapsedGameTime.TotalSeconds;
                activateGravity = true;
            }
            if (activateGravity)
                velocity.Y += gravity * (float)gameTime.ElapsedGameTime.TotalSeconds;
            else
                velocity.Y = 0;

            position += velocity;
            
            moveAnimation.Position = position;
            ssAnimation.Update(gameTime, ref moveAnimation);
            //focusing camera on x axis
            Camera.Instance.SetFocalPoint(new Vector2(position.X, ScreenManager.Instance.Dimensions.Y / 2));
        }

        public override void OnCollision(Entity e)
        {
            //collision detection with enemy result in player being sent to start of map
            Type type = e.GetType();
            if (type == typeof(Enemy))
            {
                health--;
                fileManager = new FileManager();
                fileManager.LoadContent("Load/Maps/Map1.txt", "");
                for (int i = 0; i < fileManager.Attributes.Count; i++)
                {
                    for (int j = 0; j < fileManager.Attributes[i].Count; j++)
                    {
                        switch (fileManager.Attributes[i][j])
                        {
                            case "PlayerPosition":
                                string[] split = fileManager.Contents[i][j].Split(',');
                                position = new Vector2(float.Parse(split[0]), float.Parse(split[1]));
                                break;
                        }
                    }
                }
            }
        }

        public override void Draw(SpriteBatch spriteBatch)
        {
            moveAnimation.Draw(spriteBatch);
        }

        #endregion

    }
}


