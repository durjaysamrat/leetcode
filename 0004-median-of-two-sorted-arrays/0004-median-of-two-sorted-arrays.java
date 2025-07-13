class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int k=nums1.length+nums2.length;
         int[] arr=new int[k];
         int p=0;
         int i=0;
         int j=0;
         int y=0;
         int m=nums1.length,n=nums2.length;
         while(i<m&&j<n)
         {
            if(nums1[i]<=nums2[j])
            {
             arr[p++]=nums1[i++];
            }
            else
            {
                arr[p++]=nums2[j++];
            }
         }
             while (i < m) arr[p++] = nums1[i++];
          while (j < n) arr[p++] = nums2[j++];
         if(k%2!=0)
         {
            k=k/2;
            return arr[k];
         }
         else
         {
            k=k/2;
            double o=(arr[k-1]+arr[k]);
            return o/2;
         }

    }
}