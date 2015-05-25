import UIKit

class GraphicsViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBOutlet weak var runBtn: UIButton!
    @IBOutlet weak var benchmarkResult: UILabel!
    @IBOutlet weak var imageView: UIImageView!
    
    @IBAction func run(sender: AnyObject) {
        self.runBtn.enabled = false;
        var array = [Float]();
            
        for (var times:Int = 0; times < 200; times++) {
            let bounds = CGRect(origin: CGPoint.zeroPoint, size: CGSize(width: 100, height: 100))
            let opaque = false
            let scale: CGFloat = 0
            UIGraphicsBeginImageContextWithOptions(CGSize(width: 200, height: 200), opaque, scale)
            let context = UIGraphicsGetCurrentContext()
                
            CGContextSetFillColorWithColor(context, CGColorCreate(CGColorSpaceCreateDeviceRGB(), [CGFloat(times % 256), CGFloat(256 - times % 256), CGFloat(0), CGFloat(1)]));
                
            let now = CACurrentMediaTime();
                
            let sum = self.testableMethod(context, bounds: bounds);
                
            let end = CACurrentMediaTime();
                
            array.append(Float(end - now)*100);
         }
         var str = "";
         for obj in array {
            str += obj.description + ", ";
         }
         NSLog("array: " + str);
         self.benchmarkResult.text = self.getAverage(array).description;
         self.runBtn.enabled = true;
    }
    
    func testableMethod(context: CGContext, bounds: CGRect) -> Int {
        var w = bounds.width;
        var h = bounds.height;
        for var x: CGFloat = 0; x < w; x+=2 {
            for var y: CGFloat = 0; y < h; y+=2 {
                CGContextFillRect(context, CGRectMake(x,y,1,1));
            }
        }
        
        let image = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        imageView.image = image;
        return 0;
    }

    
    func getAverage(trials: [Float]) -> Float {
        var sum: Float = 0.0;
        for trial in trials {
            sum = sum + trial;
        }
        return sum / Float(trials.count);
    }
}

