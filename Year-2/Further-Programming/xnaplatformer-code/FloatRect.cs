using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace xnaplatformer
{
    public class FloatRect
    {

        #region Variables

        float top, bottom, left, right;

        #endregion

        #region Properties

        public float Top
        {
            get { return top; }
        }

        public float Bottom
        {
            get { return bottom; }
        }

        public float Left
        {
            get { return left; }
        }

        public float Right
        {
            get { return right; }
        }

        #endregion

        #region Public Methods

        public FloatRect(float x, float y, float width, float height)
        {
            left = x;
            right = x + width;
            top = y;
            bottom = y + height;
        }

        public bool Intersects(FloatRect f)
        {
            if(right <= f.Left || Left >= f.Right || top >= f.Bottom || bottom <= f.Top)
                return false;
            return true;
        }

        #endregion

    }
}


