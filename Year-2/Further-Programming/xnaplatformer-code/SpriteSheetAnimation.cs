using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;

namespace xnaplatformer
{
    public class SpriteSheetAnimation : Animation
    {

        #region Variables

        int frameCounter, switchFrame;
        Vector2 currentFrame;

        #endregion

        #region Properties

        public SpriteSheetAnimation()
        {
            frameCounter = 0;
            switchFrame = 100;
        }

        #endregion

        #region Public Methods

        public override void Update(GameTime gameTime, ref Animation a)
        {
            // settings for the sprites
            currentFrame = a.CurrentFrame;
            if (a.IsActive)
            {
                frameCounter += (int)gameTime.ElapsedGameTime.TotalMilliseconds;
                if (frameCounter >= switchFrame)
                {
                    frameCounter = 0;
                    currentFrame.X++;

                    if (a.CurrentFrame.X * a.FrameWidth >= a.SourceRect.Width)
                        currentFrame.X = 0;
                }
            }
            else
            {
                frameCounter = 0;
                currentFrame.X = 1;
            }
            a.CurrentFrame = currentFrame;
            a.SourceRect = new Rectangle((int)a.CurrentFrame.X * a.FrameWidth, (int)a.CurrentFrame.Y * a.FrameHeight, a.FrameWidth, a.FrameHeight);
        }

        #endregion

    }
}


