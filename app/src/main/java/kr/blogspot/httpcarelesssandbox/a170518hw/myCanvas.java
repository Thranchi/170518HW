package kr.blogspot.httpcarelesssandbox.a170518hw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 윤현하 on 2017-05-18.
 */

public class myCanvas extends View {
    Bitmap mBitmap;
    Bitmap stars;
    Canvas mCanvas;
    Paint mPaint;
    String operationType = "";
    Bitmap img;
    int oldX=-1;
    int oldY=-1;
    MainActivity m=new MainActivity();
    boolean stamp=false, open=false, save=false, rotate=false, move=false, scale=false, skew=false;

    public myCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mPaint=new Paint();
        this.mPaint.setColor(Color.BLACK);
    }

    public myCanvas(Context context) {
        super(context);
        this.mPaint=new Paint();
        this.mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        setBackgroundColor(Color.BLACK);

        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);
        mCanvas.drawColor(Color.BLACK);

        img = BitmapFactory.decodeResource(getResources(), R.drawable.one);
        mCanvas.drawBitmap(img,1,1,mPaint);
    }

    //스탬프 난수생성 후 선택
    private void drawStamp() {
        switch (randomnumbercreater()) {
            case 1: {
                img = BitmapFactory.decodeResource(getResources(), R.drawable.one);
                //mCanvas.drawBitmap(img, 10, 10, mPaint);
                break;
            }
            case 2: {
                img = BitmapFactory.decodeResource(getResources(), R.drawable.two);
                //mCanvas.drawBitmap(img, 10, 10, mPaint);
                break;
            }
            case 3: {
                img = BitmapFactory.decodeResource(getResources(), R.drawable.three);
                //mCanvas.drawBitmap(img, 10, 10, mPaint);
                break;
            }
            case 4: {
                img = BitmapFactory.decodeResource(getResources(), R.drawable.four);
                //mCanvas.drawBitmap(img, 10, 10, mPaint);
                break;
            }
            case 5: {
                img = BitmapFactory.decodeResource(getResources(), R.drawable.five);
                //mCanvas.drawBitmap(img, 10, 10, mPaint);
                break;
            }
            case 6: {
                img = BitmapFactory.decodeResource(getResources(), R.drawable.six);
                //mCanvas.drawBitmap(img, 10, 10, mPaint);
                break;
            }
        }

    }

    private int randomnumbercreater() {
        if ((int) (Math.random() * 10) % 6 == 0)
            randomnumbercreater();
        return (int) (Math.random() * 10) % 6;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(mCanvas);
        mPaint.setColor(Color.BLACK);
        //스탬프
        if(stamp) {
            if(stampY>0 &&stampX>0) {
                stars = Bitmap.createScaledBitmap(img, img.getWidth() / 2, img.getHeight() / 2, false);
                //로테이트
                if(rotate)
                {
                    mCanvas.rotate(45,this.getWidth()/2,getHeight()/2);
                }

                //무브
                if(move)
                {
                    stampX+=100;
                    stampY+=100;
                }

                //스케일
                if(scale)
                {
                    stars = Bitmap.createScaledBitmap(stars, stars.getWidth() / 2, stars.getHeight() / 2, false);
                    stars = Bitmap.createScaledBitmap(stars, stars.getWidth() *3, stars.getHeight() *3, false);
                }

                //왜곡
                if(skew)
                {
                    mCanvas.skew(0.3f, 0.3f);
                }

                mCanvas.drawBitmap(stars, stampX, stampY, mPaint);

            }
        }
    }

    //지우개
    private void clear() {
            mBitmap.eraseColor(Color.BLUE);
            invalidate();
    }

    //저장
    public boolean save(String halosailor) {
        try
        {
            Context context = this.getContext();
            File storage = context.getCacheDir(); //임시파일 저장 경로

            String fileName = halosailor + ".jpg";

            File tempFile = new File(storage,fileName);

            FileOutputStream out = new FileOutputStream(tempFile);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
            Toast.makeText(getContext(), "저장완료", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch(FileNotFoundException e) {
            Log.e("FileNotFoundException", e.getMessage());
            Toast.makeText(getContext(), "저장안돼 1", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e) {
            Log.e("IOException", e.getMessage());
            Toast.makeText(getContext(), "저장안돼 2", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    //로드
    public boolean load(String halosailor){
        try
        {
        Context context = this.getContext();
        File storage = context.getCacheDir(); //임시파일 저장 경로

        String fileName = halosailor + ".jpg";

        File tempFile = new File(storage,fileName);

        FileInputStream in = new FileInputStream(tempFile);

        in.close();
       return true;
    }
        catch(FileNotFoundException e) {
        Log.e("FileNotFoundException", e.getMessage());
        Toast.makeText(getContext(), "로드안돼 1", Toast.LENGTH_SHORT).show();
    }
        catch(IOException e) {
        Log.e("IOException", e.getMessage());
        Toast.makeText(getContext(), "로드안돼 2", Toast.LENGTH_SHORT).show();
    }
        return false;
    }

    float stampX=-1,stampY=-1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == event.ACTION_DOWN) {
            if (stamp) {
                //눌린 곳 찾기
                drawStamp();
                stampX = event.getX();
                stampY = event.getY();
                invalidate();
            }
        }
        return true;
    }

    public void setOperationType(String operationType){
        this.operationType=operationType;
        invalidate();
    }



    public void getstatus() {
            if(operationType.equals("stamp")){
                if(!stamp)
                    stampY=stampX=-1;

                Toast.makeText(getContext(), stamp+"스탬프", Toast.LENGTH_SHORT).show();
            }

            else if(operationType.equals("eraser")){
                //지우개
                Toast.makeText(getContext(), operationType+"지우개의 오퍼레이션타입", Toast.LENGTH_SHORT).show();
                clear();
            }

            else if(operationType.equals("open")){
                load("halosailor");
            }

            else if(operationType.equals("save")){
                save("halosailor");
            }

            else if(operationType.equals("rotate")){
                    Toast.makeText(getContext(), operationType+"로테이트의 오퍼레이션타입", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), rotate+"로테이트", Toast.LENGTH_SHORT).show();
            }

            else if(operationType.equals("move")){
            }

            else if(operationType.equals("scale")){
            }

            else if(operationType.equals("skew")){{
            }
        }
    }

    public void setStamp(boolean stamp) {
        this.stamp = stamp;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public void setScale(boolean scale) {
        this.scale = scale;
    }

    public void setSkew(boolean skew) {
        this.skew = skew;
    }
}
