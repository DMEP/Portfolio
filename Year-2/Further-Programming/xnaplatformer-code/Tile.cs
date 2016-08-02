
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace xnaplatformer
{
    public class Tile
    {

        #region Variables

        public enum State { Solid, Passive };
        public enum Motion { Static, Horizontal, Vertical };

        State state;
        Motion motion;
        Vector2 position, prevPosition, velocity;
        Texture2D tileImage;

        float range;
        int counter;
        bool increase;
        float moveSpeed;
        bool containsEntity;

        Animation animation;

        #endregion
        
        #region Private Methods

        private Texture2D CropImage(Texture2D tileSheet, Rectangle tileArea)
        {
            Texture2D croppedImage = new Texture2D(tileSheet.GraphicsDevice, tileArea.Width, tileArea.Height);

            Color[] tileSheetData = new Color[tileSheet.Width * tileSheet.Height];
            Color[] croppedImageData = new Color[croppedImage.Width * croppedImage.Height];

            tileSheet.GetData<Color>(tileSheetData);

            int index = 0;
            for (int y = tileArea.Y; y < tileArea.Y + tileArea.Height; y++)
            {
                for (int x = tileArea.X; x < tileArea.X + tileArea.Width; x++)
                {
                    croppedImageData[index] = tileSheetData[y * tileSheet.Width + x];
                    index++;
                }
            }

            croppedImage.SetData<Color>(croppedImageData);

            return croppedImage;
        }

        #endregion

        #region Public Methods

        public void SetTile(State state, Motion motion, Vector2 position, Texture2D tileSheet, Rectangle tileArea)
        {
            // control the velocity of any moving tiles in the map
            this.state = state;
            this.motion = motion;
            this.position = position;
            increase = true;

            tileImage = CropImage(tileSheet, tileArea);
            range = 50;
            counter = 0;
            moveSpeed = 100f;
            animation = new Animation();
            animation.LoadContent(ScreenManager.Instance.Content, tileImage, "", position);
            containsEntity = false;
            velocity = Vector2.Zero;
        }

        public void Update(GameTime gameTime)
        {
            counter++;
            prevPosition = position;

            if (counter >= range)
            {
                counter = 0;
                increase = !increase;
            }

            if (motion == Motion.Horizontal)
            {
                //control the velocity of horizontal moving tiles
                if (increase)
                    velocity.X = moveSpeed * (float)gameTime.ElapsedGameTime.TotalSeconds;
                else
                    velocity.X = -moveSpeed * (float)gameTime.ElapsedGameTime.TotalSeconds;
            }
            else if (motion == Motion.Vertical)
            {
                //control the velocity of vertical moving tiles
                if (increase)
                    velocity.Y = moveSpeed *  (float)gameTime.ElapsedGameTime.TotalSeconds;
                else
                    velocity.Y = -moveSpeed * (float)gameTime.ElapsedGameTime.TotalSeconds;
            }

            position += velocity;
            animation.Position = position;

        }

        public void UpdateCollision(ref Entity e)
        {
            FloatRect rect = new FloatRect(position.X, position.Y, Layer.TileDimensions.X, Layer.TileDimensions.Y);
            // detecting if player in on a tile
            if (e.OnTile && containsEntity)
            {
                if (!e.SyncTilePosition)
                {
                    e.Position += velocity;
                    e.SyncTilePosition = true;
                }
                //detecting on which side the collision is
                if (e.Rect.Right < rect.Left || rect.Left > rect.Right || e.Rect.Bottom != rect.Top)
                {
                    e.OnTile = false;
                    containsEntity = false;
                    e.ActivateGravity = true;
                }
            }

            // detecting when player collides with a solid tile
            if (e.Rect.Intersects(rect) && state == State.Solid)
            {
                FloatRect preve = new FloatRect(e.PrevPosition.X, e.PrevPosition.Y, e.Animation.FrameWidth, e.Animation.FrameHeight);
                FloatRect prevTile = new FloatRect(prevPosition.X, prevPosition.Y, Layer.TileDimensions.X, Layer.TileDimensions.Y);

                if (e.Rect.Bottom >= rect.Top && preve.Bottom <= prevTile.Top)
                {
                    e.Position = new Vector2(e.Position.X, position.Y - e.Animation.FrameHeight);
                    e.ActivateGravity = false;
                    e.OnTile = true;
                    containsEntity = true;
                }
                else if (e.Rect.Top <= rect.Bottom && preve.Top >= prevTile.Bottom)
                {
                    e.Position = new Vector2(e.Position.X, position.Y + Layer.TileDimensions.Y);
                    e.Velocity = new Vector2(e.Velocity.X, 0);
                    e.ActivateGravity = true;
                }
                else if (e.Rect.Right >= rect.Left && preve.Right <= prevTile.Left)
                {
                    e.Position = new Vector2(position.X - e.Animation.FrameWidth, e.Position.Y);
                    e.Direction = (e.Direction == 1) ? e.Direction = 2 : e.Direction = 1;
                }
                else if (e.Rect.Left <= rect.Right && preve.Left >= prevTile.Left)
                {
                    e.Position = new Vector2(position.X + Layer.TileDimensions.X, e.Position.Y);
                    e.Direction = (e.Direction == 1) ? e.Direction = 2 : e.Direction = 1;
                }
            }
            e.Animation.Position = e.Position;

        }
        
        public void Draw(SpriteBatch spriteBatch)
        {
            animation.Draw(spriteBatch);
        }

        #endregion

    }
}


