package kr.blogspot.httpcarelesssandbox.a170518hw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    myCanvas myCanvas;
    CheckBox stamp;
    boolean bstamp=false, berase=false, bopen=false, bsave=false, brotate=false, bmove=false, bscale=false, bskew=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stamp=(CheckBox)findViewById(R.id.stamp);
        myCanvas=(myCanvas) findViewById(R.id.canvas);
        stamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    bstamp=true;
                    myCanvas.setStamp(bstamp);
                    myCanvas.setOperationType("stamp");
                    myCanvas.getstatus();
                }
                else{
                    bstamp=false;
                    myCanvas.setStamp(bstamp);
                    myCanvas.setOperationType("stamp");
                    myCanvas.getstatus();
                }
            }
        });


    }

    public void onClick(View v){
        if(v.getId()==R.id.eraser)
        {
            myCanvas.setOperationType("eraser");
            myCanvas.getstatus();
        }
        else if(v.getId()==R.id.open)
        {
            if(bopen)
            bopen=false;
            else
            bopen=true;

            myCanvas.setOpen(bopen);
            myCanvas.setOperationType("open");
            myCanvas.getstatus();
        }
        else if(v.getId()==R.id.save)
        {
            if(bsave)
                bsave=false;
            else
                bsave=true;

            myCanvas.setSave(bsave);
            myCanvas.setOperationType("save");
            myCanvas.getstatus();
        }
        else if(v.getId()==R.id.rotate)
        {
            if(!stamp.isChecked())
            stamp.setChecked(true);
            bstamp=true;
            myCanvas.setStamp(bstamp);

            if(brotate)
                brotate=false;
            else
                brotate=true;

            Toast.makeText(this, brotate+"메인로테이트", Toast.LENGTH_SHORT).show();
            myCanvas.setRotate(brotate);
            myCanvas.setOperationType("stamp");
            myCanvas.getstatus();
            myCanvas.setOperationType("rotate");
            myCanvas.getstatus();
        }
        else if(v.getId()==R.id.move)
        {
            if(!stamp.isChecked())
            stamp.setChecked(true);
            bstamp=true;
            myCanvas.setStamp(bstamp);

            if(bmove)
                bmove=false;
            else
                bmove=true;

            myCanvas.setMove(bmove);
            myCanvas.setOperationType("stamp");
            myCanvas.getstatus();
            myCanvas.setOperationType("move");
            myCanvas.getstatus();
        }
        else if(v.getId()==R.id.scale)
        {
            if(!stamp.isChecked())
            stamp.setChecked(true);
            bstamp=true;
            myCanvas.setStamp(bstamp);

            if(bscale)
                bscale=false;
            else
                bscale=true;


            myCanvas.setScale(bscale);
            myCanvas.setOperationType("stamp");
            myCanvas.getstatus();
            myCanvas.setOperationType("scale");
            myCanvas.getstatus();
        }
        else if(v.getId()==R.id.skew)
        {
            if(!stamp.isChecked())
            stamp.setChecked(true);
            bstamp=true;
            myCanvas.setStamp(bstamp);

            if(bskew)
                bskew=false;
            else
                bskew=true;


            myCanvas.setSkew(bskew);
            myCanvas.setOperationType("stamp");
            myCanvas.getstatus();
            myCanvas.setOperationType("skew");
            myCanvas.getstatus();
        }
    }
}
